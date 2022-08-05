package com.oguzhanturkmen.countries.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.oguzhanturkmen.countries.Model.Countries
import com.oguzhanturkmen.countries.NetworkViewModel
import com.oguzhanturkmen.countries.adapters.listAdapter
import com.oguzhanturkmen.countries.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class homeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var countriesListAdapter: listAdapter
    private val viewModel by lazy {
        ViewModelProvider(this).get(NetworkViewModel::class.java)
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
        countriesListAdapter = listAdapter()
        binding.listHomeFragment.adapter = countriesListAdapter
        viewModel.getObserverCountriesData().observe( viewLifecycleOwner, object: Observer<Countries>{
            override fun onChanged(t: Countries?) {
                if(t != null){
                    countriesListAdapter.submitList(t.data)
                }

            }
        })
        viewModel.loadCountries()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}