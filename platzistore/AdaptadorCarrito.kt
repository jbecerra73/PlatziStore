package com.example.platzistore

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class AdaptadorCarrito(val data: ArrayList<ItemLanding>) : RecyclerView.Adapter<AdaptadorCarrito.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemList().createView(AnkoContext.create(parent!!.context, this, false)))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindView(item = data[position])
    }

    override fun getItemCount(): Int = data.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgV: ImageView by lazy {                       //lazy inicia las variables de forma tardía
            itemView.find<ImageView>(R.id.imgItemHeader)
        }
        val txtViewTitle: TextView by lazy {
            itemView.find<TextView>(R.id.txtTitleItem)
        }

        val txtViewDesc by lazy {
            itemView.find<TextView>(R.id.txtDescItem)
        }

        val txtViewPrice by lazy {
            itemView.find<TextView>(R.id.txtPriceItem)
        }

        fun bindView(item: ItemLanding) {
            with(item) {
                txtViewTitle.text = title
                txtViewDesc.text = desc
                txtViewPrice.text = "$ ${kotlin.String.format("%.2f", price)}"
            }

        }
    }
}