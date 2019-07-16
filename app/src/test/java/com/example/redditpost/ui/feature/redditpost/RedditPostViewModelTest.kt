package com.example.redditpost.ui.feature.redditpost

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.redditpost.data.Repository
import com.example.redditpost.data.remote.RedditApi
import com.example.redditpost.data.remote.RedditResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@TestInstance(PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
internal class RedditPostViewModelTest {

    private val application = mockk<Application>(relaxed = true)
    private val redditApi = mockk<RedditApi>()
    private val redditPostVm = RedditPostViewModel(application, Repository(redditApi))

    @BeforeAll
    fun init() {
        // Replaces IO scheduler with trampoline scheduler
        Schedulers.trampoline().apply { RxJavaPlugins.setInitIoSchedulerHandler { this } }

        // Mocks any Log.d messages since JUnit test only use standard Java and Logs are apart of android.
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @Test
    fun onSuccessRanCorrectly() {
        every { redditApi.getAllPostData() } returns Single.just(emptyList())

        redditPostVm.getRedditPostData()

        assertEquals(emptyList<RedditResponse>(), redditPostVm.getRedditPostResponse().blockingObserve())
    }


    @Test
    fun onErrorRanCorrectly() {

        every { redditApi.getAllPostData() } returns Single.error(Exception("Test Error"))

        redditPostVm.getRedditPostData()

        assertEquals("Test Error", redditPostVm.getErrorLiveData().blockingObserve()?.message)
    }
}

/*Updates Live Data values immediately on the calling thread.*/
class InstantExecutorExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
    }

    override fun afterEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}

/*Waits for the Live Data to emit then returns its value.*/
fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    val observer = Observer<T> { t ->
        value = t
        latch.countDown()
    }

    observeForever(observer)

    latch.await(2, TimeUnit.SECONDS)
    return value
}