package com.deniz.watchwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.deniz.watchwithme.databinding.ActivityAnaSayfaBinding
import com.deniz.watchwithme.databinding.ActivityMainKayitOlBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AnaSayfa : AppCompatActivity() {
    private lateinit var binding: ActivityAnaSayfaBinding
    private lateinit var firestore : FirebaseFirestore
    private lateinit var landmarkList : ArrayList<Odalar>
    var adapter : LandmarkAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_sayfa)
        binding = ActivityAnaSayfaBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        landmarkList = ArrayList<Odalar>()


        firestore = FirebaseFirestore.getInstance()
        getData()

        binding.recy.layoutManager = LinearLayoutManager(this)
        adapter = LandmarkAdapter(landmarkList)
        binding.recy.adapter = adapter

        binding.createRoomBtn.setOnClickListener(){
            val intent = Intent(this@AnaSayfa,OdaOlustur::class.java)
            startActivity(intent)
        }

        binding.profilBtn.setOnClickListener(){
           val intent = Intent(this@AnaSayfa,profil::class.java)
            startActivity(intent)
        }
    }

    private fun getData(){
        firestore.collection("rooms").addSnapshotListener { value, error ->

            if(error != null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(value !=null){
                    if(!value.isEmpty){

                        landmarkList.clear()

                        val documents = value.documents

                        for(document in documents){

                            val odaismi = document.get("RoomName") as String
                            val odaboyutu = document.get("RoomSize") as String
                            val link = document.get("RoomLink") as String

                            val oda = Odalar(odaismi,odaboyutu)
                            landmarkList.add(oda)

                        }
                        adapter!!.notifyDataSetChanged()
                    }
                }
            }

        }


    }
}