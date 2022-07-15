package com.example.submission2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchRespon(
    val items: ArrayList<GithubUsers>
): Parcelable

