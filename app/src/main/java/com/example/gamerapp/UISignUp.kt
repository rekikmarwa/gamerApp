package com.example.gamerapp

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class UISignUp : AppCompatActivity() {
    private lateinit var edFullName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var edConfirmPassword: EditText
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uisign_up)

        // Initialize EditTexts and Button
        edFullName = findViewById(R.id.editTextText3)
        edEmail = findViewById(R.id.editTextText4)
        edPassword = findViewById(R.id.editTextText5)
        edConfirmPassword = findViewById(R.id.editTextText6)
        btn = findViewById(R.id.button2)

        // Set click listener for the Button
        btn.setOnClickListener { view ->
            // Get values from EditTexts
            val fullName = edFullName.text.toString()
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()
            val confirmPassword = edConfirmPassword.text.toString()

            // Flag to track errors
            var hasError = false

            // Validate fields
            if (fullName.isEmpty()) {
                edFullName.error = "Must not be empty"
                hasError = true
            }

            if (email.isEmpty()) {
                edEmail.error = "Must not be empty"
                hasError = true
            }

            if (password.isEmpty()) {
                edPassword.error = "Must not be empty"
                hasError = true
            }

            if (confirmPassword.isEmpty()) {
                edConfirmPassword.error = "Must not be empty"
                hasError = true
            }

            if (password != confirmPassword) {
                edConfirmPassword.error = "Must not be empty"
                hasError = true
            }

            if (!hasError) {
                // Handle successful registration
                handleSuccessfulRegistration(view)
            } else {
                // Display an error Snackbar
                Snackbar.make(view, "you have some errors in you inputs", Snackbar.LENGTH_LONG).show()
            }
        }

        // Create a clickable "Register Now" link
        createClickableSpan()
    }


    private fun handleSuccessfulRegistration(view: View) {
        // Code to execute on successful registration
        Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show()

        // Show a Snackbar after successful validation
        Snackbar.make(
            view,
            "Inscription réussie", // Change this message as needed
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun createClickableSpan() {
        val text = "By registering you agree to our Terms et Conditions and Privacy Policy "
        val spannableString = SpannableString(text)

        val privacyPolicyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Code to execute when "Privacy Policy" is clicked
                val snackbar = Snackbar.make(widget, " Coming soon", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
        val termsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Code to execute when "Terms et Conditions" is clicked
                Toast.makeText(this@UISignUp, "Terms et Conditions clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Set clickable spans for "Privacy Policy" and "Terms et Conditions"
        val privacyPolicyStartIndex = text.indexOf("Privacy Policy")
        val privacyPolicyEndIndex = privacyPolicyStartIndex + "Privacy Policy".length

        spannableString.setSpan(
            privacyPolicyClickableSpan,
            privacyPolicyStartIndex,
            privacyPolicyEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val termsStartIndex = text.indexOf("Terms et Conditions")
        val termsEndIndex = termsStartIndex + "Terms et Conditions".length

        spannableString.setSpan(
            termsClickableSpan,
            termsStartIndex,
            termsEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val registerNowTextView: TextView = findViewById(R.id.text_view)
        registerNowTextView.text = spannableString
        registerNowTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}
