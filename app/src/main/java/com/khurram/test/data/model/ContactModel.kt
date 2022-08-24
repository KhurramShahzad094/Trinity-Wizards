package com.khurram.test.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "contact")
@Parcelize
data class ContactModel(
    @PrimaryKey
    val id : String,
    val firstName : String?=null,
    val lastName : String?=null,
    val email : String?=null,
    val phone : String?=null,
) : Parcelable