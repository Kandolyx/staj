package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.kayitEkrani
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var destekAlButton: Button
    lateinit var kayitOlButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContentView(binding.root)
        destekAlButton = findViewById(R.id.destek_btn)
        kayitOlButton = findViewById(R.id.kayit_btn)
        binding.girisBtn.setOnClickListener {

        }

        destekAlButton.setOnClickListener {
            val intent2 = Intent(this, destekPaneli::class.java)
            startActivity(intent2)
        }

        kayitOlButton.setOnClickListener {
            val inten3 = Intent(this, kayitEkrani::class.java)
            startActivity(inten3)
        }
        binding.girisBtn.setOnClickListener{
            val kullaniciAdi=binding.kullaniciText.text.toString()
            val sifre=binding.sifreText.text.toString()
            if(kullaniciAdi.isNotEmpty() && sifre.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(kullaniciAdi,sifre).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent=Intent(this,user_panel::class.java)
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Boş alanları doldurunuz",Toast.LENGTH_SHORT).show()

            }
        }

    }
}


