package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityCustomerAddBinding
import com.example.myapplication.databinding.ActivityKayitEkraniBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class customer_add : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerAddBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kaydetBtn.setOnClickListener {
            val ad = binding.adkaydetText.text.toString()
            val soyad = binding.soyadkaydetText.text.toString()
            val tcNo = binding.tcNokaydetText.text.toString()
            val unvan = binding.unvankaydetText.text.toString()

            if (ad.isEmpty() || soyad.isEmpty() || tcNo.isEmpty() || unvan.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("user")
                    val Id = databaseReference.push().key!!
                    val userData = userData(Id,ad, soyad, tcNo , unvan)
                    databaseReference.child(Id).setValue(userData).addOnSuccessListener {
                        binding.adkaydetText.text.clear()
                        binding.soyadkaydetText.text.clear()
                        binding.tcNokaydetText.text.clear()
                        binding.unvankaydetText.text.clear()
                        Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, user_panel::class.java)
                        startActivity(intent)
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show()
                    }


            }
        }
    }