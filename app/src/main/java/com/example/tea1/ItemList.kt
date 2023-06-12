package com.example.tea1

class ItemList(var titre : String = "Titre 1", var items: MutableList<Item> = mutableListOf<Item>(Item()) ) {
    fun searchItems(description: String): List<Item> {
        return items.filter { it.description == description }
    }
    fun addItem(newItem : Item){items.add(newItem)}
    override fun toString(): String {
        return "titre : $titre; items: ${items.joinToString(prefix = ("["), postfix = "]")}"
    }
}