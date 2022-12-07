/*package com.deniz.watchwithme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deniz.watchwithme.databinding.RecylerRowBinding

class AdminAdapter(private val userList : ArrayList<RoomsUsers>) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adminrecy,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.kullaniciAdi.text = currentitem.uname

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val kullaniciAdi : TextView = itemView.findViewById(R.id.kadi)

    }

}

 */