package com.sudip.walmartapp

import android.os.Parcel
import android.os.Parcelable

data class User(
    val firstName: String?,
    val lastName: String?,
    val userName: String?,
    val password: String?
) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    @Override
    override fun equals(other: Any?): Boolean {
        if (other !is User) return false
        return other.userName == this.userName && other.password == this.password
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(userName)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}