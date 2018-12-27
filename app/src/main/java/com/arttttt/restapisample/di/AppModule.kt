package com.arttttt.restapisample.di

import com.arttttt.restapisample.model.user.UsersRepositoryImpl
import com.arttttt.restapisample.model.user.base.UsersRepository
import com.arttttt.restapisample.model.user.remote.UserApi
import com.arttttt.restapisample.presenter.userdetails.UserDetailsContract
import com.arttttt.restapisample.presenter.userdetails.UserDetailsPresenter
import com.arttttt.restapisample.presenter.users.UsersContract
import com.arttttt.restapisample.presenter.users.UsersPresenter
import com.arttttt.restapisample.presenter.usersadapter.UsersAdapterContract
import com.arttttt.restapisample.presenter.usersadapter.UsersAdapterPresenter
import org.koin.dsl.module.module

object AppModule {
    val module = module {
        single { UserApi.create() }
        single { UsersRepositoryImpl(get()) as UsersRepository }

        factory { (view: UsersContract.View) -> UsersPresenter(view, get()) as UsersContract.Presenter }
        factory { (view: UsersAdapterContract.View) -> UsersAdapterPresenter(view) as UsersAdapterContract.Presenter }
        factory { (view: UserDetailsContract.View) ->  UserDetailsPresenter(view, get()) as UserDetailsContract.Presenter }
    }
}