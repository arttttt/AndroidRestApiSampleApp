package com.arttttt.restapisample.presenter.userdetails

import com.arttttt.restapisample.model.user.User

interface UserDetailsContract {
    interface Presenter {
        var editMode: Boolean
        fun onInitialization()
        fun onAddOrUpdateUserClicked(user: User)
    }
    interface View {
        fun initialize()
    }
}