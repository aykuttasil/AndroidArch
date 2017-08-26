package com.aykutasil.playgroundandroidarch.ui.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aykutasil.playgroundandroidarch.R
import com.aykutasil.playgroundandroidarch.model.ModelUser
import com.aykutasil.playgroundandroidarch.model.ModelUserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    var lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val modelUserViewModel = ModelUserViewModel.create(this)

        modelUserViewModel.liveData?.observe(this, Observer<List<ModelUser>>() {
            val stringBuilder = StringBuilder()
            it?.forEach {
                stringBuilder.append(it.ad + " " + it.soyad)
                stringBuilder.append("\n")
            }
            Users.text = stringBuilder.toString()
        })

        // viewmodel imize değerleri yüklüyoruz
        ButtonSetUser.setOnClickListener {
            val userList = ArrayList<ModelUser>()
            var modelUser = ModelUser("Aykut", "Asil")
            userList.add(modelUser)

            modelUser = ModelUser("Kerem", "Asil")
            userList.add(modelUser)

            modelUserViewModel.addUsers(userList)
        }

        // configuratin change olduğunda viewmodel e eklenen değerler korunur. Değerler Activity tamamen yok edilene kadar(finish()) memory de saklanır.
        ButtonGetUser.setOnClickListener {
            modelUserViewModel.getUsers()?.forEach({
                println(it.ad)
                println(it.soyad)
            })
        }

        ButtonChangeUser.setOnClickListener {
            val userList = ArrayList<ModelUser>()
            var modelUser = ModelUser("Aslı", "Asil")
            userList.add(modelUser)

            modelUser = ModelUser("Ayşe", "Asil")
            userList.add(modelUser)

            modelUser = ModelUser("Ahmet", "Asil")
            userList.add(modelUser)

            modelUser = ModelUser("Hilal", "Asil")
            userList.add(modelUser)

            modelUserViewModel.changeUsers(userList)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
