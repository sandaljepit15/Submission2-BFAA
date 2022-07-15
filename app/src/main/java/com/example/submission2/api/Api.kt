package com.example.submission2.api

import com.example.submission2.GithubUsers
import com.example.submission2.SearchRespon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUsers(
        @Query("q") query: String?,
    ): Call<SearchRespon>
    @GET("users/{username}")
    fun getUsersProfil(
        @Path("username") username: String?,
    ): Call<GithubUsers>

    @GET("users/{username}/followers")
    fun userFollower(
        @Path("username") username: String?
    ): Call<List<GithubUsers>>

    @GET("users/{username}/following")
    fun userFollowing(
        @Path("username") username: String?
    ): Call<List<GithubUsers>>


}

