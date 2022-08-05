package com.oguzhanturkmen.countries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.countries.Model.Country
import com.oguzhanturkmen.countries.R

import kotlinx.android.synthetic.main.item_countries.view.*

class listAdapter: ListAdapter<Country,RecyclerView.ViewHolder>(AdapterDiffUtil) {
    object AdapterDiffUtil : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_countries,parent,false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country = getItem(position)
        holder.itemView.apply {
            countryName.text = country.name
        }
    }
}