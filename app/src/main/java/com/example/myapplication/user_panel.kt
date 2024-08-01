package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class user_panel : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<userData>
    private lateinit var database: DatabaseReference
    private lateinit var kaydetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_panel)
        recyclerView=findViewById(R.id.dataList)
        recyclerView.layoutManager=LinearLayoutManager(this)
        userArrayList= arrayListOf()
        database=FirebaseDatabase.getInstance().getReference("user")
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    for(dataSnapShot in snapshot.children){
                        val userData=dataSnapShot.getValue(userData::class.java)
                        if (userData != null) {
                            userArrayList.add(userData)
                        }


                    }
                    recyclerView.adapter= MyAdapter(userArrayList)
                }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@user_panel,error.toString(),Toast.LENGTH_SHORT).show()
            }
        }

            )
        kaydetButton=findViewById(R.id.add_btn)
        kaydetButton.setOnClickListener{
            val intent6=Intent(this,customer_add::class.java)
            startActivity(intent6)
        }
    }
}