package bharat.law.nyayasetu.client.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import bharat.law.nyayasetu.databinding.FragmentLegalAidClinicBinding


class LegalAidClinicFragment : Fragment() {
    private var _binding: FragmentLegalAidClinicBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLegalAidClinicBinding.inflate(inflater, container, false)

        binding.webView.loadUrl("https://www.probono-doj.in/home/index")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
        return binding.root
    }


}