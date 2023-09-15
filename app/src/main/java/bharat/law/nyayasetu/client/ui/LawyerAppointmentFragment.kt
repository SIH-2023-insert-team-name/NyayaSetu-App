package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentLawyerAppointmentBinding


class LawyerAppointmentFragment : Fragment() {
    private var _binding: FragmentLawyerAppointmentBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLawyerAppointmentBinding.inflate(inflater, container, false)

        binding.btnAppointment.setOnClickListener {

        }

        return binding.root
    }

}