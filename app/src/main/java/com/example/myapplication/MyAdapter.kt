package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val userList: List<userData>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.user_data,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.isimText.text=userList[position].isim
        holder.soyisimText.text=userList[position].soyisim
        holder.tcNotext.text=userList[position].tcNo
        holder.unvanText.text=userList[position].unvan
    }
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val isimText: TextView =itemView.findViewById(R.id.firstnametext)
        val soyisimText: TextView =itemView.findViewById(R.id.lastnametext)
        val tcNotext: TextView =itemView.findViewById(R.id.tcNotext)
        val unvanText: TextView =itemView.findViewById(R.id.unvantext)

    }
}