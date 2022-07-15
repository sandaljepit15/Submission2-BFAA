package com.example.submission2

import android.view.View
import submission2.databinding.ActivityMainBinding

class ObjectVisibility(private val stateShow: Int){

        fun showLoading(homeBinding: ActivityMainBinding, show: Boolean) {
            when (stateShow) {
                1 ->  {
                    if (show == false) {
                        homeBinding.progressbar.visibility = View.INVISIBLE
                    } else {
                        homeBinding.progressbar.visibility = View.VISIBLE
                        homeBinding.tvNoResult.visibility = View.INVISIBLE
                        homeBinding.rvList.visibility = View.INVISIBLE
                    }
                }

            }
        }

        fun showNoResult(homeBinding: ActivityMainBinding, show: Boolean) {
            when (stateShow) {
                1 -> {
                    if (show == false) {
                        homeBinding.tvNoResult.visibility = View.INVISIBLE
                        homeBinding.rvList.visibility = View.VISIBLE
                    } else {
                        homeBinding.tvNoResult.visibility = View.VISIBLE
                        homeBinding.rvList.visibility = View.INVISIBLE
                    }
                }
            }
        }
}