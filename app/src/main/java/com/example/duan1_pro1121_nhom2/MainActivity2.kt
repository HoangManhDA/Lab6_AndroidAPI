package com.example.duan1_pro1121_nhom2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.annotation.SuppressLint
import android.content.Context

import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    var context: Context = this
    @SuppressLint("MissingInflateId")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main3)

        var btnGet = findViewById<Button>(R.id.btnGetData)
        var tvKQ = findViewById<TextView>(R.id.tvKQ)
        val fn1 = VolleyFn1()
        btnGet!!.setOnClickListener {
            fn1.getAllData(context, tvKQ!! )
        }



        }

    }
