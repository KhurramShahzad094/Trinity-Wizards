package com.khurram.test.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ContactModel(
    val id : String?=null,
    val firstName : String?=null,
    val lastName : String?=null,
    val email : String?=null,
    val phone : String?=null,
) : Parcelable