package com.arttttt.restapisample.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arttttt.restapisample.R
import com.arttttt.restapisample.model.user.User
import com.arttttt.restapisample.presenter.users.UsersContract
import com.arttttt.restapisample.utils.ActivityUtils
import com.arttttt.restapisample.view.adapters.UsersAdapter
import kotlinx.android.synthetic.main.activity_users.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UsersActivity : AppCompatActivity(), UsersContract.View {

    private val presenter: UsersContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        fab.setOnClickListener { presenter.onFabClicked() }
        swipeLayout.setOnRefreshListener { presenter.onRefresh() }

        presenter.initialize()
    }

    override fun showErrorMessage(message: String) {
        if (message != "")
            errorMessage.visibility = View.VISIBLE
        else
            errorMessage.visibility = View.GONE

        errorMessage.text = message
    }

    override fun showLoadingIndicator(show: Boolean) {
        if (show)
            usersLoadingProgressBar.show()
        else
            usersLoadingProgressBar.hide()
    }

    override fun showRefreshingIndicator(show: Boolean) {
        swipeLayout.isRefreshing = show
    }

    override fun showUsers(users: List<User>) {
        usersRecycler.layoutManager = LinearLayoutManager(this)
        usersRecycler.adapter = UsersAdapter { user ->
            presenter.onListItemClicked(user)
        }.apply {
            presenter.putUsers(users)
        }
    }

    override fun showUserDetails(user: User?) {
        val intentExtra = if (user != null)
            Bundle().apply { putParcelable(UserDetailsActivity.UserExtraKey, user) }
        else
            null
        ActivityUtils.startActivity<UserDetailsActivity>(applicationContext, null, intentExtra, 0)
    }
}
