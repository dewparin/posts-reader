package com.blacklenspub.postsreader.data

import android.arch.lifecycle.MutableLiveData
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PostRepositoryImplTest {

    @Mock
    lateinit var localSource: PostDao
    @Mock
    lateinit var remoteSource: PostsReaderApi

    lateinit var sutRepo: PostRepositoryImpl

    val mockPosts = listOf(
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
        `when`(remoteSource.getAllPosts()).thenReturn(Single.just(mockPosts))
        val liveData = MutableLiveData<List<Post>>()
        `when`(localSource.getAllPosts()).thenReturn(liveData)

        val resultLiveData = sutRepo.getAllPosts()

        verify(localSource).insertOrUpdatePosts(*mockPosts.toTypedArray())
        assert(resultLiveData == liveData)
    }
}

