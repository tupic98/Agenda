package com.alvarenga.agenda

import android.os.Parcel
import android.os.Parcelable

class Contact(var name:String, var ID:Int, var number:Int, var dir:String, var img:Int, var isFav:Boolean ) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel. readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() as Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(ID)
        parcel.writeInt(number)
        parcel.writeString(dir)
        parcel.writeInt(img)
        parcel.writeByte((if (isFav) 1 else 0).toByte())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(`in`: Parcel): Contact {
            return Contact(`in`)
        }
        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}