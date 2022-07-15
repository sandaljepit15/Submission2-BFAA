package com.example.submission2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.RetrofitConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {





    private val _listUser = MutableLiveData<ArrayList<GithubUsers>>()
    private val _showLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val _showNoResult : MutableLiveData<Boolean> = MutableLiveData()
    private val _errorMessage : MutableLiveData<String> = MutableLiveData()



    fun getListUser(query: String?){
        _showLoading.value = true
        val client = RetrofitConfig.apiService.getSearchUsers(query)
        client.enqueue(object : retrofit2.Callback<SearchRespon>{
            override fun onResponse(
                call: Call<SearchRespon>,
                response: Response<SearchRespon>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.items != null) {
                        _listUser.value = response.body()?.items
                        _showLoading.value = false
                        _showNoResult.value = false
                    } else {
                        _showLoading.value = false
                        _showNoResult.value = true
                    }
                } else {
                    _showLoading.value = false
                    _showNoResult.value = true
                    _errorMessage.value = "failed to load data"
                }

            }

            override fun onFailure(call: Call<SearchRespon>, t: Throwable) {
                _showLoading.value = false
                _showNoResult.value = true
                _errorMessage.value = t.message.toString()
            }
        } )
    }

    fun getSearchUser():LiveData<ArrayList<GithubUsers>>{
        return _listUser
    }
    fun getLoading():LiveData<Boolean>{
        return _showLoading
    }

    fun getNoResult():LiveData<Boolean>{
      return _showNoResult
   }
    fun getError():LiveData<String>{
        return _errorMessage
    }

}