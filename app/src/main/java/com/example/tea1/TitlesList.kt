package com.example.tea1

import android.content.Context

class TitlesList(val login:String,var titles: MutableList<ItemList> = mutableListOf<ItemList>(ItemList())) {
    fun addList(newList : ItemList = ItemList()){
        titles.add(newList)
    }
    override fun toString(): String {
        return "login : $login; liste des titres: ${titles.joinToString(prefix="[", postfix = "]")}"
    }
}