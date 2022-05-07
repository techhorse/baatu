package com.techhorse.baatu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techhorse.baatu.model.UserDataModel
import com.techhorse.baatu.databinding.UserItemLayoutBinding

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var users = mutableListOf<UserDataModel>()
    fun setUserList(movies: List<UserDataModel>) {
        this.users = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemLayoutBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users[position]
        holder.binding.name.setText("Name: ${user.name}")
        user.id.toString()?.let { holder.binding.id.setText("ID: ${it}") }
        holder.binding.email.setText("Email: ${user.email}")
    }
    override fun getItemCount(): Int {
        return users.size
    }
}
class MainViewHolder(val binding: UserItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
}