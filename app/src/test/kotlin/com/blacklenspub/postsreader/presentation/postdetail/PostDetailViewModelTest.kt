package com.blacklenspub.postsreader.presentation.postdetail

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

class PostDetailViewModelTest {

    // we need to set this rule in order to execute LiveData operation current thread.
    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var postRepo: PostRepository

    lateinit var sutViewModel: PostDetailViewModel

    val mockedPostId = "1"
    val mockedPost = Post().apply {
        id = mockedPostId
        title = "1 Title"
        body = "1 Body"
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sutViewModel = PostDetailViewModel().apply { postRepo = this@PostDetailViewModelTest.postRepo }
    }

    @Test
    fun getPostDetail() {
        val repoLiveData = MutableLiveData<Post>().apply { value = mockedPost }
        `when`(postRepo.getPostById(mockedPostId)).thenReturn(repoLiveData)
        val observer = mock(Observer::class.java)

        sutViewModel.getPostDetail(mockedPostId).observeForever(observer as Observer<Post>)

        verify(postRepo).getPostById(mockedPostId)
        verify(observer).onChanged(mockedPost)
    }
}

