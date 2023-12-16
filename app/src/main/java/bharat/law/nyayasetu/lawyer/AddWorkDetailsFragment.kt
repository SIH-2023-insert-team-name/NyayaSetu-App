package bharat.law.nyayasetu.lawyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentAddPersonalDetailsBinding
import bharat.law.nyayasetu.databinding.FragmentAddWorkDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWorkDetailsFragment : Fragment() {

    private var _binding: FragmentAddWorkDetailsBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddWorkDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.availability_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.availabilitySpinner.adapter = adapter
        }

        binding.availabilitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedAvailability = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }



    }

}