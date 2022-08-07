package com.oguzhanturkmen.countries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oguzhanturkmen.countries.model.Countries.Countries
import com.oguzhanturkmen.countries.adapters.listAdapter
import com.oguzhanturkmen.countries.databinding.FragmentHomeBinding
import com.oguzhanturkmen.countries.model.Countries.Country
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class homeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var countriesListAdapter: listAdapter
    private val networkViewModel by lazy {
        ViewModelProvider(this).get(NetworkViewModel::class.java)
    }
    private val dataViewModel by lazy {
        ViewModelProvider(this).get(DatabaseViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val  view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countriesListAdapter = listAdapter(::saveCountry)
        binding.listHomeFragment.adapter = countriesListAdapter
        networkViewModel.getObserverCountriesData().observe( viewLifecycleOwner, object: Observer<Countries>{
            override fun onChanged(t: Countries?) {
                if(t != null){
                    countriesListAdapter.submitList(t.data)
                }
            }
        })
        networkViewModel.loadCountries()
        countriesListAdapter.setOnItemClickListener {
            val action = homeFragmentDirections.actionHomeFragment2ToDetailFragment(it)
            findNavController().navigate(action)
        }
        super.onViewCreated(view, savedInstanceState)
    }
    private fun saveCountry(country: Country){
        dataViewModel.saveCountry(country)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}