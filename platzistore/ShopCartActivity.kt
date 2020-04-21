package com.example.platzistore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shop_cart.*
import org.jetbrains.anko.db.select

class ShopCartActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rcCart.layoutManager = layoutManager
        rcCart.setHasFixedSize(true)

        val items = arrayListOf<ItemLanding>()

        (0..2).map { items.add(ItemLanding("Item $it", "Desc $it", it + 100.0)) }

        rcCart.adapter = AdaptadorCarrito(items)
    }

    override fun onResume() {
        super.onResume()
        val db = DBOpenHelper.getInstance(this)
        db?.use {
            select("Productos").exec {
                Log.e("Columnas", "${this.columnCount}")
                Log.e("Columnas", "${this.columnNames.size}")

                (this.columnNames).map {
                    Log.e("Columna: ", "$it")
                    Log.e("Columna: ", "${this.getColumnIndex(it)}")
                    Log.e("Columna: ", "${this.getColumnName(this.getColumnIndex(it))}")
                }

                this.moveToFirst()          //mueve el cursor a la primera pos y ahi lo arranca a recorrer
                do {
                    Log.e("VALUE", this.getString(1) ?:"")   //titulo en DBOpen helper
                    Log.e("VALUE", this.getString(2) ?:"")   //descripcion en DBOpen helper
                    Log.e("VALUE", "$this.getDouble(3)")   //precio en DBOpen helper
                }while(this.moveToNext())

            }
        }
    }
}