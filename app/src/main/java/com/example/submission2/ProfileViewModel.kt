package com.example.submission2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.RetrofitConfig
import retrofit2.Call
import retrofit2.Response

class ProfileViewModel : ViewModel() {


    private val userProfil = MutableLiveData<GithubUsers>()
    private val _showLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val _errorMessage : MutableLiveData<String> = MutableLiveData()


    fun getUserProfil(username: String?){
        _showLoading.value = true
        val client = RetrofitConfig.apiService.getUsersProfil(username)
        client.enqueue(object : retrofit2.Callback<GithubUsers>{

            override fun onResponse(call: Call<GithubUsers>, response: Response<GithubUsers>) {
                if (response.isSuccessful) {
                    userProfil.value = response.body()
                    _showLoading.value = false
                }else{
                    _showLoading.value = false
                    _errorMessage.value = "failed to load data"
                }
            }

            override fun onFailure(call: Call<GithubUsers>, t: Throwable) {
                _showLoading.value = false
                _errorMessage.value = t.message.toString()
            }


        } )
    }

    fun getProfil(): LiveData<GithubUsers> {
        return userProfil

    }    fun getLoading():LiveData<Boolean>{
        return _showLoading
    }
    fun getError():LiveData<String>{
        return _errorMessage
    }

}