package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback

class UserListAdapter(private val callback: Listener) : RecyclerView.Adapter<ListUserViewHolder>() {

    private var users: MutableList<User> = mutableListOf()

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

    override fun getItemCount(): Int = users.size

    fun updateList(newList: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        users = newList.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateUserState(position: Int, isActive: Boolean) {
        users[position].isActive = isActive
        notifyItemChanged(position)
    }

    fun getUsers(): List<User> = users

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val movedUser = users.removeAt(fromPosition)
        users.add(toPosition, movedUser)
        notifyItemMoved(fromPosition, toPosition)
    }
}
