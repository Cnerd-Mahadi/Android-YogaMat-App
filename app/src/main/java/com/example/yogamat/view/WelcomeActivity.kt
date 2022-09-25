package com.example.yogamat.view

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.yogamat.R
import com.example.yogamat.databinding.ActivityWelcomeBinding

class WelcomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.welcomeButton.setOnClickListener {
            Intent(this, YogaDashActivity:: class.java).also {
                startActivity(it)

            }
        }

    }
}