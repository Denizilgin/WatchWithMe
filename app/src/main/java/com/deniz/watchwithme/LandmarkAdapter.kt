package com.deniz.watchwithme

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deniz.watchwithme.databinding.RecylerRowBinding



class LandmarkAdapter(val landmarkList : ArrayList<Odalar>) : RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>() {


    class LandmarkHolder(val binding : RecylerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val binding = RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        holder.binding.recyclerViewRoomName.text = landmarkList.get(position).name


        holder.itemView.setOnClickListener(){
            val intent = Intent(holder.itemView.context,WatchRoomJ::class.java)
            intent.putExtra("Odalar",landmarkList.get(position))
            holder.itemView.context.startActivity(intent)




        }

    }

    override fun getItemCount(): Int {
        return landmarkList.size
    }

}