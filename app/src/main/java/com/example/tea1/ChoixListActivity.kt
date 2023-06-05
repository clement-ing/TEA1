package com.example.tea1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class ChoixListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)
        val list: RecyclerView = findViewById<RecyclerView>(R.id.list)
        //val postItems = getActivityList()
        //list.adapter = PostAdapter(dataSet = postItems?.listTitles ?: listOf(""))
        //list.layoutManager = LinearLayoutManager(this)
    }



    private val pseudo = intent.getStringExtra("name")
    //val pseudo = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("pseudo", "NoPseudo")

    fun getActivityList() : DataType?{
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonString = prefs.getString("$pseudo", null)
        var activityList : DataType?=null
        if (jsonString != null) {
            val gson = Gson()
            activityList = gson.fromJson(jsonString, DataType::class.java)
        }
        return activityList
    }





    class PostAdapter(
        private val dataSet : List<String>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val holder = PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
            )
            return holder
        }

        override fun getItemCount(): Int = dataSet.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as PostViewHolder).bind(dataSet.get(position))
        }

    }

    class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(postItem : String) {
            val title  = itemView.findViewById<TextView>(R.id.post_title)
            title.text = postItem
        }

    }

    data class DataType(
        val listTitles:List<String>,
        val itemListList:List<List<Item>>
    )

}





