package com.blacklenspub.postsreader.data

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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
        val dbLiveData = MutableLiveData<List<Post>>().apply { value = mockedPosts }
        `when`(localSource.getAllPosts()).thenReturn(dbLiveData)

        val resultLiveData = sutRepo.getAllPosts()

        verify(remoteSource).getAllPosts()
        verify(localSource).insertOrUpdatePosts(*mockedPosts.toTypedArray())
        verify(localSource).getAllPosts()
        assertThat(resultLiveData.value, IsEqual(mockedPosts))
    }

    @Test
    fun getPostById() {
        val postId = "1"
        val mockedPost = mockedPosts.first { it.id == postId }
        `when`(remoteSource.getPostById(postId)).thenReturn(Single.just(mockedPost))
        val dbLiveData = MutableLiveData<Post>().apply { value = mockedPost }
        `when`(localSource.getPostById(postId)).thenReturn(dbLiveData)

        val resultLiveData = sutRepo.getPostById(postId)

        verify(remoteSource).getPostById(postId)
        verify(localSource).insertOrUpdatePosts(mockedPost)
        verify(localSource).getPostById(postId)
        assertThat(resultLiveData.value, IsEqual(mockedPost))
    }
}

