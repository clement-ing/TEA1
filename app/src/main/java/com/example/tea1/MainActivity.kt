package com.example.tea1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.activity_main_title) //titre du menu
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val btnOK = findViewById<Button>(R.id.btnOK)
        val editText = findViewById<EditText>(R.id.edtPseudo)
        editText.setText(sharedPreferences.getString("pseudo","NoPseudo"))

        btnOK.setOnClickListener {
            val pseudoInput = findViewById<EditText>(R.id.edtPseudo).text.toString() //pseudo entre
            val pseudos = sharedPreferences.getStringSet("pseudos",mutableSetOf(""))
            //on ajoute le pseudo a la liste des pseudos si il est nouveau
            if (pseudos?.contains(pseudoInput) == false){
                pseudos.add(pseudoInput)
                editor.putStringSet("pseudos", pseudos)
                editor.apply()
                //on implémente une liste basique pour le nouvel utilisateur
                val gson = Gson()
                val defaultObject = ChoixListActivity.DataType(
                    listOf("Liste 1"),
                    listOf(listOf(Item("Item1", false)))
                )
                val jsonString = gson.toJson(defaultObject)
                editor.putString("$pseudoInput", jsonString)
                editor.apply()
            }
            //on met a jour le pseudo actuel
            editor.putString("pseudo", pseudoInput)
            editor.apply()

            val intent = Intent(this, ChoixListActivity::class.java)
            //Log.i("pseudoInput", pseudoInput)
            //intent.putExtra("name", pseudoInput)
            //on pourrait egalement recuperer ce pseudo depuis les preferences
            startActivity(intent)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        //Toast.makeText(this.applicationContext, "menuInflated", Toast.LENGTH_SHORT).show()
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Gérer les actions de menu ici
        return when (item.itemId) {
            R.id.menu_item1 -> {
                //val pseudoInput = findViewById<EditText>(R.id.edtPseudo).text.toString()
                val intent = Intent(this, SettingsActivity::class.java)
                //intent.putExtra("lastName", pseudoInput)
                //Toast.makeText(this, "$pseudoInput", Toast.LENGTH_LONG).show()
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }

}