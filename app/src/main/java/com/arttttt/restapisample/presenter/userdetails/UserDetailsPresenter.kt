package com.arttttt.restapisample.presenter.userdetails

import com.arttttt.restapisample.model.user.User
import com.arttttt.restapisample.model.user.base.UsersRepository

class UserDetailsPresenter(private val view: UserDetailsContract.View,
                           private val usersRepository: UsersRepository): UserDetailsContract.Presenter {

    override var editMode = false

    override fun onInitialization() {
        view.initialize()
    }

    override fun onAddOrUpdateUserClicked(user: User) {
        if (editMode)
            usersRepository.updateUser(user)
        else
            usersRepository.addUser(user)
    }

}