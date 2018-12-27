package com.arttttt.restapisample.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.arttttt.restapisample.presenter.usersadapter.UsersAdapterContract
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item.*

class UsersViewHolder(override val containerView: View,
                      itemClick: (Int) -> Unit): RecyclerView.ViewHolder(containerView),
    UsersAdapterContract.ViewHolder,
    LayoutContainer {

    init {
        container.setOnClickListener { itemClick(adapterPosition) }
    }

    override fun setFirstName(firstName: String) {
        firstNameText.text = firstName
    }

    override fun setLastName(lastName: String) {
        lastNameText.text = lastName
    }

    override fun setEmail(email: String) {
        emailText.text = email
    }
}