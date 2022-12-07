package com.deniz.watchwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deniz.watchwithme.databinding.ActivityMainKayitOlBinding
import com.deniz.watchwithme.databinding.ActivityOdaOlusturBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


private lateinit var binding : ActivityOdaOlusturBinding
private lateinit var firestore : FirebaseFirestore
private lateinit var landmarkList : ArrayList<Odalar>


class OdaOlustur : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOdaOlusturBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        landmarkList = ArrayList<Odalar>()
        firestore = FirebaseFirestore.getInstance()



        binding.odaolusturBtn.setOnClickListener() {
            clickCreate()
        }

        }
        fun clickCreate(){

            val roomname = binding.roomName.text.toString()
            val roomsize = binding.roomSize.text.toString()
            val roomlink = binding.roomlink.text.toString()
            val oda = Odalar(roomname,roomsize)


            if (roomname.isNotEmpty() && roomsize.isNotEmpty()) {
                val dataw = hashMapOf<String,Any>()
                dataw.put("RoomName",roomname)
                dataw.put("RoomSize",roomsize)
                dataw.put("RoomLink",roomlink)

                firestore.collection("rooms").document(roomname)
                    .set(dataw, SetOptions.merge()).addOnCompleteListener() {
                        Toast.makeText(
                            this,
                            roomname + " isimli oda başarıyla oluşturuldu.",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this@OdaOlustur, AnaSayfa::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener() {
                        Toast.makeText(
                            this,
                            roomname + " isimli oda OLUŞTURULAMADI.",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, AnaSayfa::class.java)
                        startActivity(intent)

                    }

            }
    }

}
