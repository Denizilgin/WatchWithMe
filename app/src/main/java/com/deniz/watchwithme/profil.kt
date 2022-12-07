package com.deniz.watchwithme

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.deniz.watchwithme.databinding.ActivityAnaSayfaBinding
import com.deniz.watchwithme.databinding.ActivityProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class profil : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth


        db = FirebaseFirestore.getInstance()

        getData()
        binding.cikisYap.setOnClickListener() {
            Firebase.auth.signOut()
            Toast.makeText(this, "ÇIKIŞ BAŞARILI", Toast.LENGTH_LONG).show()
            intent = Intent(this@profil, MainActivity::class.java)
            startActivity(intent)

        }
        binding.hesapSil.setOnClickListener() {
            val user = Firebase.auth.currentUser!!
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User account deleted.")
                    }
                }
            user.let {
                val email = user.email as String
                db.collection("username-passw").document(email)
                    .delete()
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!")
                        cikisYap() }
                    .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

            }

        }


    }


    private fun getData() {
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email as String
            binding.profilIsim.text = email
            db.collection("username-passw").document(email).get().addOnSuccessListener {

                if (it.exists()) {

                    var userUsername = it.getString("username")
                    binding.profilusername.text = userUsername
                    binding.profilusername2.text = userUsername

                }

            }
                .addOnFailureListener {

                    Toast.makeText(this, "Kullanıcı Adı Yok", Toast.LENGTH_LONG).show()

                }
        }

    }
    private fun cikisYap() {

        Firebase.auth.signOut()
        Toast.makeText(this, "ÇIKIŞ BAŞARILI", Toast.LENGTH_LONG).show()
        intent = Intent(this@profil, MainActivity::class.java)
        startActivity(intent)
    }

}


