package com.arttttt.restapisample.presenter.usersadapter

import com.arttttt.restapisample.model.user.User

interface UsersAdapterContract {
    interface Presenter {
        fun bind(position: Int, holder: ViewHolder)
        fun getItemAt(position: Int): User
        fun getUsersCount(): Int
        fun putUsers(newUsers: List<User>)
    }
    interface View {
        fun notifyAdapter()
    }
    interface ViewHolder {
        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
        fun setEmail(email: String)
    }
}