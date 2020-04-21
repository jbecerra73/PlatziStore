package com.example.platzistore

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.desc.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val db = DBOpenHelper.getInstance(this)



        intent?.extras?.let {
            val titl = it.getString("title")
            val decr = it.getString("desc")
            val price = it.getDouble("price")
            txtDetailTitulo.text = titl
            txtDetailDesc.text = decr
            txtDetailPrice.text = "$ ${String.format("%.2f", price)}"

            btnBuy.setOnClickListener {

                db?.use {
                    val namePr = "name" to titl
                    val descPr = "desc" to decr
                    val pricePr = "price" to price
                    insert("Productos", namePr, descPr, pricePr)
                }
                startActivity<ShopCartActivity>()
            }
        }
    }
}
