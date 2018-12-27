package com.arttttt.restapisample

import com.arttttt.restapisample.model.user.User
import com.arttttt.restapisample.model.user.base.UsersRepository
import com.arttttt.restapisample.presenter.users.UsersContract
import com.arttttt.restapisample.presenter.users.UsersPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class UsersPresenterTest {
    private val errorText = "SAMPLE ERROR TEXT"
    private val user = User("FirstName", "LastName", "email", "ava")
    private val users = listOf(User("Test1", "Test1_last", "email1", "ava1"),
        User("Test2", "Test2_last", "email1", "ava2"),
        User("Test3", "Test3_last", "email1", "ava3"))

    private lateinit var presenter: UsersPresenter

    @RelaxedMockK
    private lateinit var view: UsersContract.View

    @RelaxedMockK
    private lateinit var repository: UsersRepository

    private val onCompletion = slot<(List<User>) -> Unit>()
    private val onError = slot<(String) -> Unit>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { repository.getUsers(onCompletion = capture(onCompletion), onError = capture(onError)) } answers {
            val onCompletion = firstArg<(List<User>) -> Unit>()
            onCompletion.invoke(users)

            val onError = secondArg<(String) -> Unit>()
            onError.invoke(errorText)
        }

        presenter = spyk(UsersPresenter(view, repository))
    }

    @Test
    fun initializeTest() {
        presenter.initialize()

        verifyOrder {
            view.showLoadingIndicator(true)
            repository.getUsers(onCompletion.captured, onError.captured)
            view.showLoadingIndicator(false)
            view.showUsers(users)
            view.showLoadingIndicator(false)
            view.showErrorMessage(errorText)
        }
    }

    @Test
    fun fabClickedTest() {
        presenter.onFabClicked()

        verify {
            view.showUserDetails(null)
        }
    }

    @Test
    fun onListItemClickedTest() {
        presenter.onListItemClicked(user)
        verify {
            view.showUserDetails(user)
        }
    }

    @Test
    fun onRefreshTest() {
        presenter.onRefresh()

        verifyOrder {
            view.showLoadingIndicator(false)
            view.showErrorMessage("")
            repository.getUsers(onCompletion.captured, onError.captured)
            view.showRefreshingIndicator(false)
            view.showUsers(users)
            view.showRefreshingIndicator(false)
            view.showErrorMessage(errorText)
        }
    }
}