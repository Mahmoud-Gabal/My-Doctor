package com.example.Doctor.data.remote.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SourceName(
    val id: String? ,
    val name: String
) : Parcelable