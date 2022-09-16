package com.example.yogamat.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yogamat.databinding.ActivityYogaDashBinding
import com.example.yogamat.model.YogaDatabase


class YogaDashActivity: AppCompatActivity() {

    private var _binding: ActivityYogaDashBinding? = null
    private val binding
        get() = checkNotNull(_binding) { throw IllegalStateException("View not bound") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityYogaDashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        YogaDatabase.invoke(this)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}