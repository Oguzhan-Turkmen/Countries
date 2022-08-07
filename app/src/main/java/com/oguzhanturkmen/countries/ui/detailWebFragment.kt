package com.oguzhanturkmen.countries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.oguzhanturkmen.countries.R
import com.oguzhanturkmen.countries.databinding.FragmentDetailBinding
import com.oguzhanturkmen.countries.databinding.FragmentDetailWebBinding
import com.oguzhanturkmen.countries.util.WIKI_KEY
import kotlinx.android.synthetic.main.fragment_detail_web.*


class detailWebFragment : Fragment() {
    private var _binding: FragmentDetailWebBinding? = null
    private val binding get() = _binding!!
    val args: detailWebFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentDetailWebBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val countryWikiCode = args.country2.wikiDataId
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(WIKI_KEY+countryWikiCode)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}