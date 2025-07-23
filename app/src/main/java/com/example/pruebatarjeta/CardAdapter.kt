package com.example.pruebatarjeta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdAdapter(private val ads: List<Ad>) : RecyclerView.Adapter<AdAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val adImage: ImageView = view.findViewById(R.id.adimage)
        val adTitle: TextView = view.findViewById(R.id.adTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ad = ads[position]
        holder.adTitle.text = ad.title
        holder.adImage.setImageResource(ad.imageResId)
    }

    override fun getItemCount(): Int = ads.size
}
