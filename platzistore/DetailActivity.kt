package com.example.platzistore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.let {                   //si no es nulo el intent, ejecutamos algo
            val textFromMain = it.extras?.getString("text").toString()
            txtDetail.text = textFromMain
        }

    }
}