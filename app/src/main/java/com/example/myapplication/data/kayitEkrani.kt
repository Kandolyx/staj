package com.example.myapplication.data

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityKayitEkraniBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.userData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class kayitEkrani : AppCompatActivity() {
    private lateinit var binding: ActivityKayitEkraniBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContentView(R.layout.activity_kayit_ekrani)
        binding = ActivityKayitEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.kayitBtn.setOnClickListener {
            val kullaniciAdi = binding.kullanicikayitText.text.toString()
            val sifre = binding.sifreText.text.toString()
            val isim = binding.isimkayitText.text.toString()
            val email = binding.emailkayitText.text.toString()
            if (kullaniciAdi.isNotEmpty() && sifre.isNotEmpty() && isim.isNotEmpty() && email.isNotEmpty()) {
                databaseReference = FirebaseDatabase.getInstance().getReference("admin")
                val adminId = databaseReference.push().key!!
                val userData = userData(adminId, kullaniciAdi, isim, email, sifre)
                databaseReference.child(adminId).setValue(userData).addOnSuccessListener {
                    binding.isimkayitText.text.clear()
                    binding.kullanicikayitText.text.clear()
                    binding.emailkayitText.text.clear()
                    binding.sifreText.text.clear()
                    firebaseAuth.createUserWithEmailAndPassword(email, sifre)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)

                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
            }

        }

    }}