package com.example.platzistore

import android.app.Activity
import android.content.Intent
import androidx.core.util.Pair
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_landing.view.*
import org.jetbrains.anko.startActivity
import java.net.URI.create

class AdapterLanding(val data: List<ItemLanding?>?) : RecyclerView.Adapter<AdapterLanding.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(parent?.inflate(R.layout.item_landing))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        data?.let {
            holder?.bindView(it[position])
        }
    }
    override fun getItemCount(): Int = data?.size ?: 0

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bindView(itemLanding: ItemLanding?) {
            itemLanding?.let {
                with(it) {
                    itemView.txtTitleItem.text = title
                    itemView.txtDescItem.text = desc
                    itemView.txtPriceItem.text = "$ ${String.format("%.2f", price)}"

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailActivity::class.java)
                        intent.putExtra("title", title)
                        intent.putExtra("desc", desc)
                        intent.putExtra("price", price)

                        val p1: Pair<View, String> = Pair.create(itemView.imgItemHeader, "transitionHeader")
                        val p2: Pair<View, String> = Pair.create(itemView.txtTitleItem, "transitionTitle")
                        val p3: Pair<View, String> = Pair.create(itemView.txtDescItem, "transitionDesc")
                        val p4: Pair<View, String> = Pair.create(itemView.txtPriceItem, "transitionPrice")
                        val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity, p1, p2, p3, p4)
                        itemView.context.startActivity(intent, options.toBundle())  }
                }
            }
        }
    }
}
