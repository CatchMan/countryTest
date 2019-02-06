package com.example.catchman.counrytz.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.model.CountryInfo
import com.example.catchman.counrytz.R
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private var onClickListener: View.OnClickListener) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var countries: List<CountryInfo> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        if (position in 0 until countries.size) {
            holder.tvTitle.text = countries[position].nativeName
            holder.itemView.setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = countries.size

    fun updateData(basicModels: List<CountryInfo>){
        this.countries = basicModels
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tvTitle
    }
}