package com.example.platzistore

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx:Context) :ManagedSQLiteOpenHelper(ctx, "PlatziStore", null, 1){

    companion object{
        private var instance:DBOpenHelper? = null

        fun getInstance(ctx: Context): DBOpenHelper? = if (instance == null){       //si la instancia es nula, la creamos
            instance = DBOpenHelper(ctx)
            instance
        }else{
            instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val id = "id" to INTEGER + PRIMARY_KEY      //estas
        val name = "name" to TEXT                   //son las
        val desc = "desc" to TEXT                   //columnas
        val price = "price" to REAL                 //de la DB

        db?.createTable("Productos", true, id, name, desc, price)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.dropTable("Productos", true)

    }

    val Context.database: DBOpenHelper?
    get() = getInstance(applicationContext)

}