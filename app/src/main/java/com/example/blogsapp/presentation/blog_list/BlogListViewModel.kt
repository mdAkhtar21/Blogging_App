package com.example.blogsapp.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogsapp.data.mapper.toBlogList
import com.example.blogsapp.domain.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.blogsapp.data.util.Result
import com.example.blogsapp.presentation.blog_list.components.BlogListEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


class BlogListViewModel(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BlogListState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )
    init {
        getAllBlogs()
    }

    private val _event= Channel<BlogListEvent>()
    val event=_event.receiveAsFlow()

    private fun getAllBlogs() {
        viewModelScope.launch {
            val result = blogRepository.getAllBlogs()

            when (result) {
               is Result.Success->{
                   _state.update { it.copy(isLoading=true) }
                   _state.update {
                       it.copy(blogs = result.data.orEmpty().reversed(),
                           errorMessage = null,
                           isLoading = false
                       )
                   }
               }
               is Result.Error->{
                   _state.update {
                       it.copy(blogs = result.data.orEmpty(),
                           errorMessage = result.message,
                           isLoading = false
                       )
                   }
                   result.message?.let {
                       _event.send(BlogListEvent.Error(it))
                   }

               }
            }
        }
    }
}
