package com.example.blogsapp.presentation.blog_content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.blogsapp.data.util.Result
import com.example.blogsapp.domain.repository.BlogRepository
import com.example.blogsapp.presentation.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogContentViewModel(
    savedStateHandle: SavedStateHandle,
    private val blogRepository: BlogRepository
): ViewModel() {

    val blogId = savedStateHandle.toRoute<Route.BlogContentScreen>().blogId

    private val _state= MutableStateFlow(BlogContentState());
    val state=_state
        .onStart{
            getBlogById()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    fun onAction(action: BlogContentAction){
        when(action){
            BlogContentAction.Refresh -> getBlogById()
        }
    }

    private fun getBlogById(){
        viewModelScope.launch {
            val result=blogRepository.getBlogById(blogId)
            when(result){
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = result.message,
                            blog = result.data
                        )
                    }
                }
                is Result.Success -> {
                    _state.update { it.copy(isLoading=true) }
                    _state.update {
                        it.copy(
                            errorMessage = null,
                            blog = result.data,
                            isLoading = false
                        )
                    }
                }
            }

        }
    }
}