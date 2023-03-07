package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var lists: MutableList<Wishlist> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val itemName = findViewById<EditText>(R.id.iteminput)
        val itemPrice = findViewById<EditText>(R.id.price)
        val itemURL = findViewById<EditText>(R.id.url)
        val Rv = findViewById<RecyclerView>(R.id.recycler)

        var adapter = Wishlist_adapter(lists)
        Rv.adapter = adapter
        Rv.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener{
            val name = itemName.text.toString()
            val price = itemPrice.text.toString()
            val url = itemURL.text.toString()
            val newItem = Wishlist(name, price, url)

            lists.add(newItem)
            adapter.notifyDataSetChanged()

        }


    }
}