package bharat.law.nyayasetu.nonProfitOrg

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.viewpager2.widget.ViewPager2
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentNgoDashboardBinding
import bharat.law.nyayasetu.databinding.FragmentPartnershipBinding

class PartnershipFragment : Fragment() {

    private var _binding: FragmentPartnershipBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPartnershipBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.loadUrl("https://dreamgirlfoundation.ngo/become-a-fund-raiser/?gad_source=1&gclid=CjwKCAiAvoqsBhB9EiwA9XTWGap8rpbY60r8yIMeFW21HtKeheYXJleRQe4ojlJF1fXM7uXNnHWkzxoCinwQAvD_BwE")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
    }
}