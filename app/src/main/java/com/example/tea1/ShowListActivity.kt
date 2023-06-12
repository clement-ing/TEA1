package com.example.tea1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tea1.R.id.list_items

class ShowListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        setTitle(R.string.activity_show)
        val list: RecyclerView = findViewById<RecyclerView>(list_items)
        val postItems = TitlesList("pseudo").titles
        postItems.add(ItemList("Titre 2"))
        list.adapter = PostAdapter(dataSet = postItems)
        list.layoutManager = LinearLayoutManager(this)
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