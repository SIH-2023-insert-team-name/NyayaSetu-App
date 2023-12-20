package bharat.law.nyayasetu.lawyer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.client.ui.ChatFragment
import bharat.law.nyayasetu.databinding.FragmentLawyerDashboardBinding
import bharat.law.nyayasetu.databinding.FragmentUserCaseDetailsBinding


class UserCaseDetailsFragment : Fragment() {

    private var _binding: FragmentUserCaseDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserCaseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAccept.setOnClickListener { 
            navigateToChats()
        }
        
        binding.btnReject.setOnClickListener {
            Toast.makeText(requireContext(), "Case Rejected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToChats() {
        val fragmentToNavigateTo = LawyerChatFragment() // Replace with your actual Fragment class

        val fragmentManager =
            requireActivity().supportFragmentManager // Use requireActivity() if you are inside a Fragment, or getActivity() if inside an Activity
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.navHostFragmentLawyer,
            fragmentToNavigateTo
        ) // R.id.fragment_container is the ID of the container where you want to replace the Fragment
        transaction.addToBackStack(null) // This allows the user to navigate back to the previous Fragment
        transaction.commit()
    }
}