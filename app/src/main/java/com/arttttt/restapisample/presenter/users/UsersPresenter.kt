package com.arttttt.restapisample.presenter.users

import com.arttttt.restapisample.model.user.User
import com.arttttt.restapisample.model.user.base.UsersRepository

open class UsersPresenter(private val view: UsersContract.View,
                     private val usersRepository: UsersRepository): UsersContract.Presenter {
    override fun initialize() {
        view.showLoadingIndicator(true)

        usersRepository.getUsers({ users ->
            view.showLoadingIndicator(false)
            view.showUsers(users)
        }, { errorMessage ->
            view.showLoadingIndicator(false)
            view.showErrorMessage(errorMessage)
        })
    }

    override fun onFabClicked() {
        view.showUserDetails(null)
    }

    override fun onListItemClicked(user: User) {
        view.showUserDetails(user)
    }

    override fun onRefresh() {
        view.showLoadingIndicator(false)
        view.showErrorMessage("")
        usersRepository.getUsers({ users ->
            view.showRefreshingIndicator(false)
            view.showUsers(users)
        }, { errorMessage ->
            view.showRefreshingIndicator(false)
            view.showErrorMessage(errorMessage)
        })
    }
}