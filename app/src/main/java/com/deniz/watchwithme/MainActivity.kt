package com.deniz.watchwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deniz.watchwithme.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseFirestore
    private lateinit var landmarkList : ArrayList<Odalar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if(currentUser != null){
            val intent = Intent(this,AnaSayfa::class.java)
            startActivity(intent)
            finish()
        }



        binding.girisYap.setOnClickListener(){
            val email = binding.usernameInput.text.toString()
            val password = binding.pass.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        //Signed In
                        Toast.makeText(applicationContext,"Hoşgeldin: ${auth.currentUser?.email.toString()}",Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, AnaSayfa::class.java)
                        startActivity(intent)
                        finish()

                    }

                }.addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.kayitolGecis.setOnClickListener(){
            intent = Intent(applicationContext,MainKayitOl::class.java)
            startActivity(intent)
        }
    }
}