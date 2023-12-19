package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentClientPersonalDetailsBinding
import bharat.law.nyayasetu.models.ClientPersonalDetailsData
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientPersonalDetailsFragment : Fragment() {

    private var _binding: FragmentClientPersonalDetailsBinding? = null

    var selectedGender: String? = null
    var preferredlanguage: String? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentClientPersonalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.indian_languages_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.preferredLanguageSpinner.adapter = adapter
        }

        binding.btnNext.setOnClickListener {
            val clientPersonalData = ClientPersonalDetailsData(
                name = binding.etName.text.toString(),
                aadhar = binding.etAadhar.text.toString(),
                gender = selectedGender,
                age = binding.etAge.text.toString(),
                city = binding.etCity.text.toString(),
                preferredLanguages = preferredlanguage
            )

            AppSession(requireContext()).putObject(
                Constants.CLIENT_PERSONAL_DETAILS,
                clientPersonalData
            )

            findNavController().navigate(R.id.action_clientPersonalDetailsFragment_to_homeFragment)
        }


        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedGender = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        binding.preferredLanguageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                preferredlanguage = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

}