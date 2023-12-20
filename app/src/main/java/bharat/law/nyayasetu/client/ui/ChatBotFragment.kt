package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentChatBotBinding
import bharat.law.nyayasetu.databinding.FragmentFirebaseChatBinding


class ChatBotFragment : Fragment() {

    private var _binding: FragmentChatBotBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.loadUrl("https://main--heartfelt-cheesecake-ffd97f.netlify.app/")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
    }

}