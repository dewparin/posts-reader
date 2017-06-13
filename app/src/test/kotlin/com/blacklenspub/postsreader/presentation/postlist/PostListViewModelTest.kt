package com.blacklenspub.postsreader.presentation.postlist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.entity.Post
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PostListViewModelTest {

    // we need to set this rule in order to execute LiveData operation current thread.
    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var postRepo: PostRepository

    lateinit var sutViewModel: PostListViewModel

    val mockedPosts = listOf(
            Post().apply {
                id = "1"
                title = "1 Title"
                body = "1 Body"
            },
            Post().apply {
                id = "2"
                title = "2 Title"
                body = "2 Body"
            },
            Post().apply {
                id = "3"
                title = "3 Title"
                body = "3 Body"
            }
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sutViewModel = PostListViewModel().apply { postRepo = this@PostListViewModelTest.postRepo }
    }

    @Test
    fun getAllPosts() {
        val repoLiveData = MutableLiveData<List<Post>>().apply { value = mockedPosts }
        `when`(postRepo.getAllPosts()).thenReturn(repoLiveData)
        val observer = mock(Observer::class.java)

        sutViewModel.getAllPosts().observeForever(observer as Observer<List<Post>>)

        verify(observer).onChanged(mockedPosts)
    }
}

