package com.example.tea1

class Item(var description:String = "Item1", var done:Boolean = false) {

    override fun toString(): String {
        return "(description : ${this.description}; done: ${this.done.toString()})"
    }

}