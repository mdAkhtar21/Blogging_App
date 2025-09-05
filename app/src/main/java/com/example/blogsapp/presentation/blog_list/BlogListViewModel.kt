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

class BlogListViewModel(private val blogRepository: BlogRepository) : ViewModel() {

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

    private fun getAllBlogs() {
        viewModelScope.launch {
            val blog = blogRepository.getAllBlogs()

            if (blog != null) {
                _state.update {
                    it.copy(blogs = blog)
                }
            }
        }
    }
}
