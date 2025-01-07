package com.openclassrooms.magicgithub.ui.user_list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User

class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        Glide.with(binding.root.context)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.itemListUserAvatar)

        binding.itemListUserUsername.text = user.login

        val backgroundColor = if (user.isActive)
            ContextCompat.getColor(binding.root.context, R.color.cardview_light_background)
        else
            ContextCompat.getColor(binding.root.context, R.color.design_default_color_error)

        binding.root.setBackgroundColor(backgroundColor)

        binding.itemListUserDeleteButton.setOnClickListener { callback.onClickDelete(user) }
    }
}
