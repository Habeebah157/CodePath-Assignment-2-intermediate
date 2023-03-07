package com.example.wishlist

import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class Wishlist_adapter(private val Wishlist: List<Wishlist>):RecyclerView.Adapter<Wishlist_adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item: TextView
        val cost: TextView
        val url: TextView


        init {
            item = itemView.findViewById(R.id.item)
            cost = itemView.findViewById(R.id.price)
            url = itemView.findViewById(R.id.url)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listView = inflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder(listView)
    }

    override fun getItemCount(): Int {
        return Wishlist.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = Wishlist.get(position)
        holder.item.text = task.itemName
        holder.cost.text = task.price
        holder.url.text = task.url
    }
}