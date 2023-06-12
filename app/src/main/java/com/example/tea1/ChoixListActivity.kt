package com.example.tea1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class ChoixListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)
        setTitle(R.string.activity_choix)
        val btnAdd = findViewById<Button>(R.id.btnAddList)
        val edtTitre = findViewById<EditText>(R.id.edtTitre)
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        //private val pseudo = intent.getStringExtra("name")
        val pseudo = prefs.getString("pseudo", "NoPseudo")
        val data : TitlesList = getActivityList(pseudo, prefs)
        val list: RecyclerView = findViewById<RecyclerView>(R.id.list)
        val postItems = data.titles
        data.addList(ItemList("Titre 2"))
        list.adapter = PostAdapter(dataSet = postItems)
        list.layoutManager = LinearLayoutManager(this)
        btnAdd.setOnClickListener{
            val titre = edtTitre.text.toString()
            data.addList(ItemList(titre))
            (list.adapter as PostAdapter).notifyDataSetChanged()
            //Log.i("Data:", "${data.toString()}")
        }

    }


    private fun getActivityList(pseudo:String?, prefs: SharedPreferences) : TitlesList{
        when(pseudo){
            null->throw Exception("pseudo non trouvé")
            else->{val gson = Gson()
                val jsonString = prefs.getString("$pseudo", null)
                Log.i("jsonString","$jsonString")
                var activityList = TitlesList(pseudo)
                if (jsonString != null) {
                    val gson = Gson()
                    activityList = gson.fromJson(jsonString, TitlesList::class.java)
                }
                else throw Exception("pseudo non trouvé")
                return activityList
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Gérer les actions de menu ici
        return when (item.itemId) {
            R.id.menu_item1 -> {
                //val pseudoInput = findViewById<EditText>(R.id.edtPseudo).text.toString()
                val intent = Intent(this, SettingsActivity::class.java)
                //intent.putExtra("lastName", pseudoInput)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }





    class PostAdapter(
        private val dataSet : List<ItemList>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
            )
        }

        override fun getItemCount(): Int = dataSet.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as PostViewHolder).bind(dataSet[position].titre)
        }

    }

    class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(postItem : String) {
            val title  = itemView.findViewById<TextView>(R.id.post_title)
            title.text = postItem
        }

    }


}





