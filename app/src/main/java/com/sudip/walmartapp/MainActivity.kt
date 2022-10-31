package com.sudip.walmartapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sudip.walmartapp.activity.RegisterActivity
import com.sudip.walmartapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userInputList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userInputList.add(User("Sudip", "Adhikari", "sudip@gmail.com", "test123"))
        userInputList.add(User("Sanjay", "Rimal", "sanjay@gmail.com", "test123"))
        userInputList.add(User("Alex", "Magar", "alex@gmail.com", "test123"))
        userInputList.add(User("Pratik", "Shrestha", "pratik@gmail.com", "test123"))
        userInputList.add(User("Akash", "Aryal", "akash@gmail.com", "test123"))

        binding.signInButton.setOnClickListener {
            val userEmail = binding.emailET.editableText.toString()
            val userPassword = binding.passwordET.editableText.toString()

            if (userEmail.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid username",
                Toast.LENGTH_SHORT
            ).show()
            else if (userPassword.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid password",
                Toast.LENGTH_SHORT
            ).show()
            else {
                val user = User("", "", userEmail, userPassword)
                var found = false
                userInputList.forEach {
                    if (it == user) {
                        found = true
                        startActivity(Intent(
                            this,
                            UserShoppingByCategory::class.java
                        ).apply {
                            putExtra("username", userEmail)
                        })
                    }
                }

                if (!found) Toast.makeText(
                    this,
                    "Invalid User. Please Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.createAccountButton.setOnClickListener {
            resultLauncher.launch(Intent(this, RegisterActivity::class.java))
        }

        binding.forgotPasswordTV.setOnClickListener {
            val email = binding.emailET.editableText.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter username to change password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var password: String? = ""
            userInputList.forEach {
                if (it.userName == email) {
                    password = it.password
                    return@forEach
                }
            }

            if (password!!.isEmpty()) {
                Toast.makeText(this, "Username not found!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mailto = "mailto:$email" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Change your Password") +
                    "&body=" + Uri.encode("Your password is: $password")

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: User = result.data?.extras?.get("user") as User
                userInputList.add(data)
            }
        }
}