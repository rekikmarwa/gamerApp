package com.example.gamerapp
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class UILogin : AppCompatActivity() {

    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var btn: Button
    private lateinit var forgotPassword: TextView
    private lateinit var linkC: TextView
    private lateinit var imageButton5: View
    private lateinit var imageButton7: View

    private val PREFS_NAME = "my_preferences"
    private val SPLASH_SCREEN_SHOWN = "splash_screen_shown"
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uilogin)

        // Initialisation des vues
        linkC = findViewById(R.id.textView4)
        edEmail = findViewById(R.id.editTextText7)
        edPassword = findViewById(R.id.editTextText2)
        btn = findViewById(R.id.button2)
        forgotPassword = findViewById(R.id.tvForgotPassword)
        imageButton5 = findViewById(R.id.imageButton5)
        imageButton7 = findViewById(R.id.imageButton7)

        // Lien "Forgot Password"
        forgotPassword.setOnClickListener {
            val intent = Intent(this@UILogin, UIForgotPassword::class.java)
            startActivity(intent)
        }

        // Création du texte cliquable "Register Now"
        val text = "Don't have an account ? Register Now"
        val spannableString = SpannableString(text)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Code à exécuter lorsque le lien est cliqué
                Toast.makeText(this@UILogin, "Register Now clicked", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@UILogin, UISignUp::class.java)
                startActivity(intent)
            }
        }

        // Définir la plage de texte cliquable (dans cet exemple, "Register Now")
        val startIndex = text.indexOf("Register Now")
        val endIndex = startIndex + "Register Now".length

        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        linkC.text = spannableString
        linkC.movementMethod = LinkMovementMethod.getInstance()

        // Gestion du clic sur l'imageButton5 (peut être déplacé ailleurs si nécessaire)
        imageButton5.setOnClickListener {
            val snackbar = Snackbar.make(imageButton5, "Coming soon", Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        // Gestion du clic sur l'imageButton7 (peut être déplacé ailleurs si nécessaire)
        imageButton7.setOnClickListener {
            val snackbar = Snackbar.make(imageButton7, "Coming soon", Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        // Si l'écran de bienvenue n'a pas encore été affiché, lancez-le
        if (!isSplashScreenShown()) {
            setSplashScreenShown(true)

            // Utilisation d'un Handler pour retarder le lancement de LoginActivity
            val handler = Handler()
            handler.postDelayed({
                val myIntent = Intent(this@UILogin, UISplashScreen::class.java)
                startActivity(myIntent)
                finish() // Fermer LoginActivity
            }, 2000) // Attendre 2 secondes (2000 millisecondes) avant de passer à LoginActivity
        } else {
            // L'écran de bienvenue a déjà été affiché, vous pouvez continuer normalement
        }

        // Gestion du clic sur le bouton principal
        btn.setOnClickListener { view ->
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()

            var hasError = false

            if (email.isEmpty()) {
                edEmail.error = "Must not be empty"
                hasError = true
            } else if (!isValidEmail(email)) {
                edEmail.error = "you have some errors in you inputs"
                hasError = true
            }

            if (password.isEmpty()) {
                edPassword.error = "Must not be empty"
                hasError = true
            } else if (!isValidPassword(password)) {
                edPassword.error = "you have some errors in you inputs"
                hasError = true
            }

            if (!hasError) {
                // Si aucune erreur n'a été détectée, exécutez votre code ici
                Toast.makeText(this, "Bouton cliqué", Toast.LENGTH_SHORT).show()

                // Ajoutez cette partie pour passer à l'activité HomeActivity
                val intent = Intent(this@UILogin, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        // Vous pouvez ajouter des critères de validation du mot de passe ici
        return password.length >= 6
    }

    private fun isSplashScreenShown(): Boolean {
        return sharedPreferences.getBoolean(SPLASH_SCREEN_SHOWN, false)
    }

    private fun setSplashScreenShown(isShown: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(SPLASH_SCREEN_SHOWN, isShown)
        editor.apply()
    }
}
