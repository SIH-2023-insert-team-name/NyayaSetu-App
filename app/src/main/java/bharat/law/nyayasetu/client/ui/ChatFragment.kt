package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
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

//        binding.payment.setOnClickListener {
//            navigateToPayment()
//        }

        binding.webView.loadUrl("https://65823b3bd35d1c0a9c63900d--charming-basbousa-e9b0c1.netlify.app/")

        binding.webView.getSettings().setJavaScriptEnabled(true)

        binding.webView.setWebViewClient(WebViewClient())
        return binding.root
    }

    private fun navigateToPayment() {
        val fragmentToNavigateTo = PaymentFragment() // Replace with your actual Fragment class

        val fragmentManager =
            requireActivity().supportFragmentManager // Use requireActivity() if you are inside a Fragment, or getActivity() if inside an Activity
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.navHostFragment,
            fragmentToNavigateTo
        ) // R.id.fragment_container is the ID of the container where you want to replace the Fragment
        transaction.addToBackStack(null) // This allows the user to navigate back to the previous Fragment
        transaction.commit()
    }

}