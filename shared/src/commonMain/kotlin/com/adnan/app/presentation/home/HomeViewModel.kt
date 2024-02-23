package com.adnan.app.presentation.home

import com.adnan.app.presentation.model.RepoUiModel
import com.adnan.app.presentation.model.toUi
import com.adnan.core.Error
import com.adnan.core.Result
import com.adnan.core.map
import com.adnan.domain.usecase.GetSearchRepoUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class HomeViewModel(
    private val getSearchRepoUseCase: GetSearchRepoUseCase
) : ViewModel() {

    init {
        setSearchQuery()
    }

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    private val _uiRepos =
        MutableStateFlow<Result<PersistentList<RepoUiModel>>?>(null)
    val uiRepos = _uiRepos.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    fun onSearchTextChanged(org: String) {
        searchQuery.value = org
    }

    @FlowPreview
    private fun setSearchQuery() {
        viewModelScope.launch {
            searchQuery
                .debounce(500)
                .collectLatest { query ->
                    if (query.isNotBlank())
                        searchRepository()
                }

        }
    }

    private fun searchRepository() {
        cancelJobs()
        viewModelScope.launch(job) {
            getSearchRepoUseCase(repo = searchQuery.value)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    _uiRepos.value = Result.Failure(
                        Error(
                            message = throwable.message ?: "",
                            throwable = throwable
                        )
                    )
                }
                .collect { result ->
                    _uiRepos.value =
                        result.map { list ->
                            list.map {
                                it.toUi(
                                    checkFakeFavorite(it.id)
                                )
                            }.toPersistentList()
                        }
                }
        }
    }

    private fun checkFakeFavorite(repoId: Int) =
        fakeStoreFavoriteRepoId.contains(repoId)

    private fun cancelJobs() {
        job.cancel()
    }

    /*
     * Fake Store Favorite Repos
     */

    private val fakeStoreFavoriteRepoId: ArrayList<Int> = arrayListOf()

    fun updateFakeFavorite(repoUiModel: RepoUiModel) {
        if (repoUiModel.isLiked) addFakeFavorite(repoUiModel.id)
        else removeFakeFavorite(repoUiModel.id)
    }

    private fun addFakeFavorite(id: Int) {
        fakeStoreFavoriteRepoId.add(id)
        updateRepo(id)
    }

    private fun removeFakeFavorite(id: Int) {
        fakeStoreFavoriteRepoId.remove(id)
        updateRepo(id)
    }

    private fun updateRepo(id: Int) {
        _uiRepos.update { result ->
            result?.map { persistentList ->
                persistentList.map { repoUi ->
                    if (repoUi.id == id) {
                        repoUi.copy(isLiked = checkFakeFavorite(id))
                    } else {
                        repoUi
                    }
                }.toPersistentList()
            }
        }
    }
}