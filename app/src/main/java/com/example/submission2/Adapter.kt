package com.example.submission2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import submission2.databinding.ListUserBinding
import java.util.ArrayList

class Adapter(private val listUser: ArrayList<GithubUsers>) : RecyclerView.Adapter<Adapter.ListViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")



    fun setData(items: List<GithubUsers>){
        listUser.apply {
            addAll(items)
        }
        notifyDataSetChanged()
    }

    private var onListClick: OnListClick? = null


    fun setOnListClick(onListClick: OnListClick){
        this.onListClick = onListClick
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ListUserBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListViewHolder(private val binding: ListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GithubUsers) {
            println("user $user")
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .apply(RequestOptions().override(50,50))
                .into(binding.listPhoto)
            binding.listUsername.text =  user.login
            itemView.setOnClickListener {onListClick?.onItemClicked(user)}
        }
    }
    interface OnListClick {
        fun onItemClicked(data: GithubUsers)
    }
}