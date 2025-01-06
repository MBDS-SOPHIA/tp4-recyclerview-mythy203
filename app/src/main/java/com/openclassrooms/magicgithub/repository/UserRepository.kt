package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(
    private val apiService: ApiService
) {
    fun getUsers(): List<User> {
        //("Should return the list of users retrieved from the api service")
        return apiService.getUsers()

    }

    fun addRandomUser() {
        //("Should add a random user")
        apiService.addRandomUser()

    }

    fun deleteUser(user: User) {
        //("Should remove the user")
        apiService.deleteUser(user)

    }
}