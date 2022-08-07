package com.oguzhanturkmen.countries.ui

import android.graphics.Bitmap
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load

import com.bumptech.glide.RequestBuilder
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.countries.databinding.FragmentDetailBinding
import com.oguzhanturkmen.countries.model.Country.CountryDetail
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class detailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: detailFragmentArgs by navArgs()
    private var requestBuilder: RequestBuilder<PictureDrawable>? = null
    private lateinit var webView: WebView
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
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        webView = binding.detailWebViewImage
        webView.settings.javaScriptEnabled
        webView.settings.loadWithOverviewMode
        webView.settings.useWideViewPort
        webViewImage(webView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val countryArgs = args.country

        networkViewModel.loadCountryDetail(countryArgs.code)
        networkViewModel.getObserverCountryDetail()
            .observe(viewLifecycleOwner, object : Observer<CountryDetail> {
                override fun onChanged(t: CountryDetail?) {
                    if (t != null) {
                        webView.loadUrl(t.data.flagImageUri)
                    }
                }
            })
        binding.detailFragmentCountryCode.text = countryArgs.code
        binding.detailMoreBtn.setOnClickListener {
            countryArgs.let {
                val action = detailFragmentDirections.actionDetailFragmentToDetailWebFragment(it)
                findNavController().navigate(action)
            }
        }
        binding.favImage.setOnClickListener {
            dataViewModel.saveCountry(countryArgs)
            Snackbar.make(view,"Country Saved", Snackbar.LENGTH_SHORT).show()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    fun webViewImage(webView: WebView){
        webView.webViewClient = object : WebViewClient() {
            val imageLoader = ImageLoader.Builder(requireContext())
                .components{
                    add(SvgDecoder.Factory())
                }.build()
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.detailImage.load(url,imageLoader)
                Log.e("das",url.toString())
            }
        }
    }
}