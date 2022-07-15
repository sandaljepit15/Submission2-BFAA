package com.example.submission2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUsers(
    val login: String?,
    val id: Int?,
    val avatar_url: String?,
    val name: String?,
    val location: String?,
    val company: String?,
    val public_repos: String?,
    val followers: String?,
    val following: String?,
):Parcelable
