package com.masorone.cryptocompare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        loadData()
    }

    private fun initViewModel() {
        vm = ViewModelProvider(
            this,
            MainViewModelFactory(context = applicationContext)
        )[MainViewModel::class.java]
    }

    private fun loadData() {
        vm.loadData()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}