package com.example.submission2

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import submission2.R
import submission2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object{
        const val stateShow = 1
    }


    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var show = ObjectVisibility(stateShow)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvList.setHasFixedSize(true)
        val mainModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        mainViewModel = mainModel
        mainModel.getSearchUser().observe(this, {
            if (it != null) {
                showRecycleView(it)
            }
        })
        mainModel.getLoading().observe(this, {
            show.showLoading(binding, it)
        })
        mainModel.getNoResult().observe(this, {
            show.showNoResult(binding, it)
        })
        mainModel.getError().observe(this, {
         showError(it)
        })

     show.showLoading(binding, show = false)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchUsers(query)
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()){
                    show.showNoResult(binding, show = true)
                }
                return true
            }
        })
        return true
    }
    private fun searchUsers(query: String?){
            mainViewModel.getListUser(query)
    }



    private fun showRecycleView(searchUser: ArrayList<GithubUsers>) {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(searchUser)
        binding.rvList.adapter = adapter
        adapter.setOnListClick(object : Adapter.OnListClick {
            override fun onItemClicked(data: GithubUsers) {
                    detailUser(data)  }
        })
    }
    private fun detailUser(user: GithubUsers){
        val intent = Intent(this@MainActivity,ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.SELECTED_USER, user)
        startActivity(intent)
    }
    private fun showError(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}