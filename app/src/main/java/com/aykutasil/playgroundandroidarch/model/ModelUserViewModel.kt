package com.aykutasil.playgroundandroidarch.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

/**
 * Created by aykutasil on 26.08.2017.
 */
class ModelUserViewModel : ViewModel() {

    var liveData: MutableLiveData<List<ModelUser>>? = MutableLiveData()

    fun getUsers(): List<ModelUser>? {
        return liveData?.value
    }

    fun addUsers(userList: List<ModelUser>) {
        liveData?.value = userList
    }

    fun changeUsers(userList: List<ModelUser>) {
        liveData?.value = userList
    }

    fun abc(){

    }

    companion object {
        fun create(activity: AppCompatActivity): ModelUserViewModel {
            val productDetailViewModel = ViewModelProviders.of(activity).get(ModelUserViewModel::class.java)
            return productDetailViewModel
        }
    }

}