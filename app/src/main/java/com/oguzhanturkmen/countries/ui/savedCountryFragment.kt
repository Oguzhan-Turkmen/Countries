package com.oguzhanturkmen.countries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.countries.adapters.listAdapter
import com.oguzhanturkmen.countries.databinding.FragmentSavedCountryBinding
import com.oguzhanturkmen.countries.model.Countries.Country
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class savedCountryFragment : Fragment() {
    private var _binding: FragmentSavedCountryBinding? =null
    private val binding get() = _binding!!
    private lateinit var countriesListAdapter: listAdapter
    private val dataViewModel by lazy {
        ViewModelProvider(this).get(DatabaseViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedCountryBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        countriesListAdapter = listAdapter(::saveCountry)
        binding.listSavedFragment.adapter = countriesListAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countriesListAdapter.setOnItemClickListener {
            val action = savedCountryFragmentDirections.actionSavedCountryFragment2ToDetailFragment(it)
            findNavController().navigate(action)
        }
        dataViewModel.savedlivedata.observe(viewLifecycleOwner, {
            countriesListAdapter.submitList(it)
        }
        )
        dataViewModel.getSavedCountries()
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val country = countriesListAdapter.currentList[position]
                dataViewModel.deleteMovies(country)
                Snackbar.make(view, "DELETED", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        dataViewModel.saveCountry(country)
                        dataViewModel.getSavedCountries()
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.listSavedFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
    private fun saveCountry(country: Country){
        dataViewModel.saveCountry(country)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}