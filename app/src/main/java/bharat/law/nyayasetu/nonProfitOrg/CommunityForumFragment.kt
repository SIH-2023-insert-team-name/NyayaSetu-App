package bharat.law.nyayasetu.nonProfitOrg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentCommunityForumBinding
import bharat.law.nyayasetu.databinding.FragmentLawyerDashboardBinding


class CommunityForumFragment : Fragment() {


    private var _binding: FragmentCommunityForumBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommunityForumBinding.inflate(inflater, container, false)

       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.loadUrl("https://www.cede.co.in/empanelled-network/legal-organisations-and-ngos")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
    }

}