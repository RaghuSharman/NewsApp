package com.tensorware.newsapp.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tensorware.newsapp.MainApp
import com.tensorware.newsapp.model.ArticleCategory
import com.tensorware.newsapp.model.TopNewsResponse
import com.tensorware.newsapp.model.getArticleCategory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = getApplication<MainApp>().repository

    private val _newsResponse = MutableStateFlow(TopNewsResponse())
    val newsResponse: StateFlow<TopNewsResponse>
        get() = _newsResponse

    // check loading state variable
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
//    val isError: StateFlow<Boolean> = _isError     -> method 1
    // method 2
    val isError: StateFlow<Boolean>
    get() = _isError

    val errorHandler = CoroutineExceptionHandler{
        _,error->
        if (error is Exception){
            _isError.value = true
        }
    }

    // create method to launch to get the article method
    fun getTopArticles() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _newsResponse.value = repository.getArticles()
            _isLoading.value = false
        }


    }

    // getter function
    private val _getArticleByCategory = MutableStateFlow(TopNewsResponse())
    val getArticleByCategory: StateFlow<TopNewsResponse>
        get() = _getArticleByCategory


    // setter function
    private val _selectedCategory: MutableStateFlow<ArticleCategory?> = MutableStateFlow(null)
    val selectedCategory: StateFlow<ArticleCategory?>
        get() = _selectedCategory

    fun getArticlesByCategory(category: String) {

        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _getArticleByCategory.value = repository.getArticlesByCategory(category = category)
            _isLoading.value = false
        }
    }

    val sourceName = MutableStateFlow("engadget")
    private val _getArticleBySource = MutableStateFlow(TopNewsResponse())
    val getArticleBySource: StateFlow<TopNewsResponse>
        get() = _getArticleBySource

    val query = MutableStateFlow("")
    private val _searchedNewsResponse = MutableStateFlow(TopNewsResponse())
    val searchedNewsResponse: StateFlow<TopNewsResponse>
        get() = _searchedNewsResponse


    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category = category)
        _selectedCategory.value = newCategory
    }


    fun getArticleBySource(){
        _isLoading.value = true

        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _getArticleBySource.value = repository.getArticlesBySource(sourceName.value)
            _isLoading.value = false
        }
    }
// get searched
    fun getSearchedArticles(query: String){
    _isLoading.value = true

    viewModelScope.launch(Dispatchers.IO) {
        _searchedNewsResponse.value = repository.getSearchedArticles(query = query)
        _isLoading.value = false
    }
    }


}