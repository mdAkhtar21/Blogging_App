package com.example.blogsapp.presentation.blog_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentScreen(
    state: BlogContentState,
    onBackClick: () -> Unit,
    onAction:(BlogContentAction)->Unit
) {
    val scrollBehavior=TopAppBarDefaults.enterAlwaysScrollBehavior()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        BlogContentTopBar(
            scrollBehavior=scrollBehavior,
            onBackClick = onBackClick,
            title = state.blog?.title
        )
        when{
            state.errorMessage!=null->{
                ErrorContent(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = state.errorMessage,
                    onRefreshClick = {onAction(BlogContentAction.Refresh)}
                )
            }
            else->{
                MainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    blogContent = state.blog?.content
                )
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    blogContent: String?
) {
    Column(modifier = modifier) {
        MarkdownText(
            markdown = blogContent ?: "",
            linkColor = MaterialTheme.colorScheme.secondary,
            isTextSelectable = true,
            syntaxHighlightColor = MaterialTheme.colorScheme.surfaceVariant,
            syntaxHighlightTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogContentTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    title: String?
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        windowInsets = WindowInsets(0.dp), // ✅ Correct way
        modifier = modifier,
        title = {
            Text(
                text = title ?: "Blog Content",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        }
    )
}


@Composable
private fun ErrorContent(
    modifier: Modifier=Modifier,
    errorMessage:String,
    onRefreshClick:()->Unit
){
    Column(modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        IconButton(
            onClick = onRefreshClick
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(modifier=Modifier.height(20.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}