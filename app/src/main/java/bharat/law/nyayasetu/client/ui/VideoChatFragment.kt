package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentChatBinding
import bharat.law.nyayasetu.databinding.FragmentVideoChatBinding

class VideoChatFragment : Fragment() {
    private var _binding: FragmentVideoChatBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVideoChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.loadUrl("https://video-conf-frontend.vercel.app/")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())

    }

}