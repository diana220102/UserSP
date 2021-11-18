package com.cursosant.android.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursosant.android.userssp.databinding.ItemUserAltBinding

/****
 * Project: Users SP
 * From: com.cursosant.android.userssp
 * Created by Alain Nicolás Tello on 20/11/20 at 11:58
 * Course: Android Practical with Kotlin from zero.
 * All rights reserved 2021.
 *
 * All my Courses(Only on Udemy):
 * https://www.udemy.com/user/alain-nicolas-tello/
 ***/
class UserAdapter(private val users: List<User>, private val listener: OnClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_user_alt, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)

        with(holder){
            setListener(user, position+1)
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = user.getFullName()
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemUserAltBinding.bind(view)

        fun setListener(user: User, position: Int){
            binding.root.setOnClickListener { listener.onClick(user, position) }
        }
    }
}