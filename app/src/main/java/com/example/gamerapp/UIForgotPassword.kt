package com.example.gamerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class UIForgotPassword : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var buttonSendSMS: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uiforgot_password)

        editEmail = findViewById(R.id.editEmail)
        buttonSubmit = findViewById(R.id.buttonS)
        buttonSendSMS = findViewById(R.id.button1)

        // Désactiver les boutons au démarrage
        buttonSubmit.isEnabled = true
        buttonSendSMS.isEnabled = true

        setupSubmitButton()
        setupSendSMSButton()
        setupAutoValidation()
    }

    private fun setupSubmitButton() {
        buttonSubmit.setOnClickListener {
            val enteredEmail = editEmail.text.toString()

            if (isValidEmail(enteredEmail)) {
                // Si l'e-mail est valide, naviguer vers OTPValidationActivity avec le code spécifié (1234)
                navigateToOTPValidationActivity("1234")
            } else {
                // Si les données ne sont pas valides, afficher un Snackbar avec un message d'erreur
                Snackbar.make(it, "you have some errors in you inputs", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupSendSMSButton() {
        buttonSendSMS.setOnClickListener {
            val enteredEmail = editEmail.text.toString()

            if (isValidEmail(enteredEmail)) {
                // Si l'e-mail est valide, naviguer vers OTPValidationActivity avec le code 6789
                navigateToOTPValidationActivity("6789")
            } else {
                // Si les données ne sont pas valides, afficher un Snackbar avec un message d'erreur
                Snackbar.make(it, "you have some errors in you inputs", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupAutoValidation() {
        editEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val enteredEmail = s.toString()

                if (isValidEmail(enteredEmail)) {
                    // Si l'e-mail est valide, activer les boutons
                    buttonSubmit.isEnabled = true
                    buttonSendSMS.isEnabled = true
                } else {
                    // Si l'e-mail n'est pas valide, désactiver les boutons
                    buttonSubmit.isEnabled = false
                    buttonSendSMS.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun isValidEmail(email: String): Boolean {
        // Vérification de l'e-mail (vous pouvez personnaliser cette logique)
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun navigateToOTPValidationActivity(code: String) {
        // Naviguer vers OTPValidationActivity avec le code spécifié
        val intent = Intent(this, UIOTPValidation::class.java)
        intent.putExtra("code", code)
        startActivity(intent)
    }
}