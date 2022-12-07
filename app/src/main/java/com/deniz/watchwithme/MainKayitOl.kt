package com.deniz.watchwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deniz.watchwithme.databinding.ActivityMainKayitOlBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MainKayitOl : AppCompatActivity() {

    private lateinit var binding: ActivityMainKayitOlBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainKayitOlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()


        binding.girisDon.setOnClickListener(){
            val intent = Intent(this@MainKayitOl,MainActivity::class.java)
            startActivity(intent)
        }



        binding.kayitBtn.setOnClickListener(){
            val email = binding.email.text.toString()
            val password = binding.pass.text.toString()
            val username = binding.username.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() ){
                val data = hashMapOf(
                    "username" to username,
                    "password" to password,
                    "email" to email

                )

                firestore.collection("username-passw").document(email)
                    .set(data, SetOptions.merge())
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        val intent = Intent(this@MainKayitOl,MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this,"KAYIT BAŞARILI!",Toast.LENGTH_LONG).show()
                        finish()
                    }
                }.addOnFailureListener(){
                    Toast.makeText(this@MainKayitOl,it.localizedMessage,Toast.LENGTH_LONG).show()



                    }
                val docRef = firestore.collection("username-passw").document(email)

                val updates = hashMapOf<String, Any>(
                    "timestamp" to FieldValue.serverTimestamp()
                )

                docRef.update(updates).addOnCompleteListener { }
                }
            }


    }
}