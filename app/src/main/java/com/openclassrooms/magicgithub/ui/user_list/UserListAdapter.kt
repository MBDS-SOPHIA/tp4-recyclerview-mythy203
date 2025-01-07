package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback

class UserListAdapter(  // FOR CALLBACK ---
    private val callback: Listener
) : RecyclerView.Adapter<ListUserViewHolder>() {
    // FOR DATA ---
    private var users: List<User> = ArrayList()

    interface Listener {
        fun onClickDelete(user: User)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }

    fun updateUserState(position: Int, isActive: Boolean) {
        users[position].isActive = isActive
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // PUBLIC API ---
    fun updateList(newList: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        users = newList
        diffResult.dispatchUpdatesTo(this)
    }
    fun getUsers(): List<User> = users

}