package com.arttttt.restapisample.presenter.usersadapter

import com.arttttt.restapisample.model.user.User

class UsersAdapterPresenter(private val view: UsersAdapterContract.View): UsersAdapterContract.Presenter {

    private val users = mutableListOf<User>()


    override fun bind(position: Int, holder: UsersAdapterContract.ViewHolder) {
        val user = getItemAt(position)

        holder.setFirstName(user.firstName)
        holder.setLastName(user.lastName)
        holder.setEmail(user.email)
    }

    override fun getItemAt(position: Int) = users[position]

    override fun getUsersCount() = users.size

    override fun putUsers(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        view.notifyAdapter()
    }
}