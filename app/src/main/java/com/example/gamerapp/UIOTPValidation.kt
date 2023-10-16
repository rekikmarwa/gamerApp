
package com.example.gamerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.gamerapp.R
import com.google.android.material.snackbar.Snackbar

class UIOTPValidation : AppCompatActivity() {
    private lateinit var inputCode1: EditText
    private lateinit var inputCode2: EditText
    private lateinit var inputCode3: EditText
    private lateinit var inputCode4: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uiotpvalidation)

        inputCode1 = findViewById(R.id.inputText3)
        inputCode2 = findViewById(R.id.inputText2)
        inputCode3 = findViewById(R.id.InputText)
        inputCode4 = findViewById(R.id.inputText)

        // Appel à la fonction de configuration de l'écouteur de texte
        setupOTPInput()

        // Création du texte cliquable "Resend Code"
        val text = "Resend Code"
        val spannableString = SpannableString(text)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Afficher un SnackBar avec le message "Coming soon" lorsque le lien est cliqué
                Snackbar.make(widget, "Coming soon", Snackbar.LENGTH_SHORT).show()
            }
        }

        // Définir la plage de texte cliquable pour "Resend Code"
        val startIndex = text.indexOf("Resend Code")
        val endIndex = startIndex + "Resend Code".length

        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Afficher le texte cliquable dans un TextView
        val textView = findViewById<TextView>(R.id.textView7)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupOTPInput() {
        inputCode1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Code à exécuter avant que le texte change
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Code à exécuter lorsque le texte change
                if (s?.length == 1) {
                    // Déplacez automatiquement le focus vers le champ de saisie suivant
                    inputCode2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Code à éxecuter après que le texte a changé
            }
        })

    }
}
