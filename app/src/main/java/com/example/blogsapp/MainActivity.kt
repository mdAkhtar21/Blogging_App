package com.example.blogsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.blogsapp.presentation.blog_list.BlogListScreen
import com.example.blogsapp.presentation.blog_list.BlogListViewModel
import com.example.compose.BlogsAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = koinViewModel<BlogListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BlogsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BlogListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        event = viewModel.event
                    )
                }
            }
        }
    }
}
