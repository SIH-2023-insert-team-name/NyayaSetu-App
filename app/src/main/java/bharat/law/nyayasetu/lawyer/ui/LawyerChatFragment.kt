package bharat.law.nyayasetu.lawyer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentChatBinding
import bharat.law.nyayasetu.databinding.FragmentConsultancyBinding
import bharat.law.nyayasetu.databinding.FragmentLawyerChatBinding

class LawyerChatFragment : Fragment() {
    private var _binding: FragmentLawyerChatBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLawyerChatBinding.inflate(inflater, container, false)

        binding.webView.loadUrl("https://65823b3bd35d1c0a9c63900d--charming-basbousa-e9b0c1.netlify.app/")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
        return binding.root
    }

}