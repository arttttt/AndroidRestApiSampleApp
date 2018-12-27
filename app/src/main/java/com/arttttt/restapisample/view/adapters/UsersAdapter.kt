package com.arttttt.restapisample.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arttttt.restapisample.R
import com.arttttt.restapisample.model.user.User
import com.arttttt.restapisample.presenter.usersadapter.UsersAdapterContract
import org.koin.core.parameter.parametersOf
import org.koin.standalone.StandAloneContext

class UsersAdapter(private val itemClick: (User) -> Unit): RecyclerView.Adapter<UsersViewHolder>(), UsersAdapterContract.View {

    val presenter: UsersAdapterContract.Presenter = StandAloneContext.getKoin().koinContext.get { parametersOf(this) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.user_item, parent, false)

        return UsersViewHolder(view) { position ->
            val user = presenter.getItemAt(position)
            itemClick(user)
        }
    }

    override fun getItemCount() = presenter.getUsersCount()

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        presenter.bind(position, holder)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }
}