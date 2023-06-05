package com.example.tea1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceActivity
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class SettingsActivity : PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        //Toast.makeText(this,"${intent.getStringExtra("lastName")}",Toast.LENGTH_LONG)
        val champPseudo=findViewById<TextView>(R.id.settingsPseudo)
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        champPseudo.text = prefs.getString("pseudo", "NoPseudo")
    }
}