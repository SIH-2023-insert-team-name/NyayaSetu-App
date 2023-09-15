package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import bharat.law.nyayasetu.databinding.FragmentLawyerAppointmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        requireActivity().window.setStatusBarColor(ContextCompat.getColor(requireActivity(), R.color.white))

        binding.cvLawyerAppointment.setOnClickListener {

        findNavController().navigate(R.id.action_homeFragment_to_lawyerAppointmentFragment)
        }
        return binding.root
    }

}