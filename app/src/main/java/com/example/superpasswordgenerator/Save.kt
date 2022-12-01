package com.example.superpasswordgenerator

import android.os.Parcel
import android.os.Parcelable

data class Save(val password : String?, val site : String?, val id : String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(password)
        parcel.writeString(site)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Save> {
        override fun createFromParcel(parcel: Parcel): Save {
            return Save(parcel)
        }

        override fun newArray(size: Int): Array<Save?> {
            return arrayOfNulls(size)
        }
    }

}