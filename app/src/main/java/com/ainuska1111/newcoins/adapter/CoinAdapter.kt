package com.ainuska1111.newcoins.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ainuska1111.newcoins.R
import com.ainuska1111.newcoins.data.network.model.CoinItem
import com.ainuska1111.newcoins.ui.CoinFragment


class CoinAdapter(val data: List<CoinItem>, coinFragment: CoinFragment, fragmentItem: Int,
): RecyclerView.Adapter<CoinAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name_coin)
        val symbol: TextView = itemView.findViewById(R.id.symbol)
        val price: TextView = itemView.findViewById(R.id.price)
        val details:  LinearLayout = itemView.findViewById(R.id.details)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coins, parent, false)
        )
    }
    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.name
        holder.price.text = item.price.toString()
        holder.symbol.text = item.symbol


    }

}