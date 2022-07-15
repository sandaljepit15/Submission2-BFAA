package com.example.submission2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.RetrofitConfig
import retrofit2.Call
import retrofit2.Response

class FollowViewModel : ViewModel() {


    private val userFollow = MutableLiveData<List<GithubUsers>>()
    private val _showLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val _errorMessage : MutableLiveData<String> = MutableLiveData()




    fun getUserFollow(username: String?,follow: Int){
        _showLoading.value = true
        var client = RetrofitConfig.apiService.userFollower(username)
        if (follow == 2) {
             client = RetrofitConfig.apiService.userFollowing(username)
        }
        client.enqueue(object : retrofit2.Callback<List<GithubUsers>>{
            override fun onResponse(
                call: Call<List<GithubUsers>>,
                response: Response<List<GithubUsers>>
            ) {
                userFollow.value = response.body()
                _showLoading.value = false
            }

            override fun onFailure(call: Call<List<GithubUsers>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        } )
    }
    fun followUser():LiveData<List<GithubUsers>>{
        return userFollow
    }
    fun getLoading():LiveData<Boolean>{
        return _showLoading
    }
    fun getError():LiveData<String>{
        return _errorMessage
    }

}

