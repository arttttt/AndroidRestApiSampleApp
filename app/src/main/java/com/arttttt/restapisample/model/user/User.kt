package com.arttttt.restapisample.model.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(@SerializedName("first_name")
                @Expose
                val firstName: String,
                @SerializedName("last_name")
                @Expose
                val lastName: String,
                @SerializedName("email")
                @Expose
                val email: String,
                @SerializedName("avatar_url")
                @Expose
                val avatarUrl: String) : Parcelable