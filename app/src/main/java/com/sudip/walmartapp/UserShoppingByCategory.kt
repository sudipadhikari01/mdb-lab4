package com.sudip.walmartapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.sudip.walmartapp.databinding.ActivityShoppingCategoryBinding

class UserShoppingByCategory : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ss: String = intent.getStringExtra("username").toString()
        binding.welcomeText.text = "Welcome: $ss"


        binding.clothingLayout.setOnClickListener {
            showToast("Clothing")
        }

        binding.foodLayout.setOnClickListener {
            showToast("Food")
        }

        binding.electronicLayout.setOnClickListener {
            showToast("Electronics")
        }

        binding.beautyLayout.setOnClickListener {
            showToast("Beauty")
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this, "You have chosen the $msg category of shopping", Toast.LENGTH_SHORT
        ).show()
    }
}