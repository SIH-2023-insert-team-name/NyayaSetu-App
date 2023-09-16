package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentBookAppointmentBinding
import bharat.law.nyayasetu.databinding.FragmentHomeBinding

class BookAppointmentFragment : Fragment() {
    private var _binding: FragmentBookAppointmentBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}