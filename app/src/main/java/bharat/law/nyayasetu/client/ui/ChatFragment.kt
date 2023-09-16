package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentChatBinding
import bharat.law.nyayasetu.databinding.FragmentConsultancyBinding

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.webView.loadUrl("https://65054722dad673658c37186a--wondrous-bunny-2e7121.netlify.app/")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
        return binding.root
    }

}