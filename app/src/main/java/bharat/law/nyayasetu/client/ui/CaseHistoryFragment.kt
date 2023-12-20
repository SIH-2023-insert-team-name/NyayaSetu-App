package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentCaseHistoryBinding
import bharat.law.nyayasetu.databinding.FragmentHomeBinding

class CaseHistoryFragment : Fragment() {
    private var _binding: FragmentCaseHistoryBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCaseHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.cvVideo1.setOnClickListener {
                findNavController().navigate(R.id.action_caseHistoryFragment_to_videoChatFragment)
            }
        binding.cvVideo2.setOnClickListener {
            findNavController().navigate(R.id.action_caseHistoryFragment_to_videoChatFragment)
        }
    }

}