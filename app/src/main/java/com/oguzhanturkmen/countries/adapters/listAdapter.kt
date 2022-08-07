package com.oguzhanturkmen.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.countries.model.Countries.Country
import com.oguzhanturkmen.countries.databinding.ItemCountriesBinding

import kotlinx.android.synthetic.main.item_countries.view.*

class listAdapter( private val onSaveCallback: (Country) -> Unit): ListAdapter<Country,RecyclerView.ViewHolder>(AdapterDiffUtil) {

    object AdapterDiffUtil : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
    inner class CountriesViewHolder(val binding:ItemCountriesBinding) : RecyclerView.ViewHolder(binding.root) {
      fun bind(model: Country ){
          binding.star.setOnClickListener {
              onSaveCallback(model)
              binding.isSelected = !binding.isSelected
          }
          binding.root.apply {
              countryName.text = model.name
              setOnClickListener {
                  onItemClickListener?.let { it(model) }
              }
          }
          }
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = CountriesViewHolder(ItemCountriesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        return view
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country = getItem(position)
        (holder as? CountriesViewHolder)?.bind(country)
    }

    private var onItemClickListener: ((Country) -> Unit)? = null
    fun setOnItemClickListener(listener: (Country) -> Unit) {
        onItemClickListener = listener
    }
    }

