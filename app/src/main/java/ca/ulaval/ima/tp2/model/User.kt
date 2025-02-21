package ca.ulaval.ima.tp2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val gender: String,
    val department: String
) : Parcelable