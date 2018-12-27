package com.arttttt.restapisample.presenter.users

import com.arttttt.restapisample.model.user.User

interface UsersContract {
    interface Presenter {
        fun initialize()
        fun onFabClicked()
        fun onListItemClicked(user: User)
        fun onRefresh()
    }

    interface View {
        fun showErrorMessage(message: String)
        fun showLoadingIndicator(show: Boolean)
        fun showRefreshingIndicator(show: Boolean)
        fun showUsers(users: List<User>)
        fun showUserDetails(user: User?)
    }
}