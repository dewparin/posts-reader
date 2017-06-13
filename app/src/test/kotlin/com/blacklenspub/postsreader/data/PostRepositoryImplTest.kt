package com.blacklenspub.postsreader.data

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PostRepositoryImplTest {

    // we need to set this rule in order to execute LiveData operation current thread.
    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var localSource: PostDao
    @Mock
    lateinit var remoteSource: PostsReaderApi

    lateinit var sutRepo: PostRepositoryImpl

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
        sutRepo = PostRepositoryImpl(localSource, remoteSource, Schedulers.trampoline())
    }

    @Test
    fun getAllPosts() {
        `when`(remoteSource.getAllPosts()).thenReturn(Single.just(mockedPosts))
        `when`(localSource.getAllPosts()).thenReturn(MutableLiveData<List<Post>>().apply { value = mockedPosts })
        val observer = mock(Observer::class.java)

        sutRepo.getAllPosts().observeForever(observer as Observer<List<Post>>)

        verify(remoteSource).getAllPosts()
        verify(localSource).insertOrUpdatePosts(*mockedPosts.toTypedArray())
        verify(localSource).getAllPosts()
        verify(observer).onChanged(mockedPosts)
    }

    @Test
    fun getPostById() {
        val postId = "1"
        val mockedPost = mockedPosts.first { it.id == postId }
        `when`(remoteSource.getPostById(postId)).thenReturn(Single.just(mockedPost))
        `when`(localSource.getPostById(postId)).thenReturn(MutableLiveData<Post>().apply { value = mockedPost })
        val observer = mock(Observer::class.java)

        sutRepo.getPostById(postId).observeForever(observer as Observer<Post>)

        verify(remoteSource).getPostById(postId)
        verify(localSource).insertOrUpdatePosts(mockedPost)
        verify(localSource).getPostById(postId)
        verify(observer).onChanged(mockedPost)
    }
}

