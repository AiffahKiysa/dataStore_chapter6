package com.example.datastore_chapter6.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.datastore_chapter6.data.DataUserManager
import kotlinx.coroutines.launch

class UserViewModel(private val pref: DataUserManager): ViewModel() {
    fun saveUser(username: String, name: String, email: String, password: String){
        viewModelScope.launch {
            pref.setUsername(username)
            pref.setPassword(password)
            pref.setName(name)
            pref.setEmail(email)
        }
    }

    fun getDataUsername(): LiveData<String> {
        return pref.getUsername().asLiveData()
    }

    fun getDataPassword(): LiveData<String>{
        return pref.getPassword().asLiveData()
    }

    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }

    fun getEmail(): LiveData<String>{
        return pref.getEmail().asLiveData()
    }

    fun setIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            pref.setIsLogin(isLogin)
        }
    }

    fun getIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin().asLiveData()
    }


}