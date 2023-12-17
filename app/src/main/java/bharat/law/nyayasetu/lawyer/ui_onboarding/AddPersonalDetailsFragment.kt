package bharat.law.nyayasetu.lawyer.ui_onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentAddPersonalDetailsBinding
import bharat.law.nyayasetu.models.PersonalDetailsData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonalDetailsFragment : Fragment() {

    private var _binding: FragmentAddPersonalDetailsBinding? = null

    var selectedGender: String? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPersonalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lspType = arguments?.getString("lspType")

        binding.btnNext.setOnClickListener{
            val personalDetails = PersonalDetailsData(
                binding.etName.text.toString(),
                binding.etAadhar.text.toString(),
                selectedGender,
                binding.etAge.text.toString().toInt()
            )
            val bundle = Bundle()
            bundle.putString("lspTpe", lspType)
            bundle.putParcelable("personalDetails", personalDetails)
            val fragment = AddWorkDetailsFragment()
            fragment.arguments = bundle
            findNavController().navigate(R.id.action_addPersonalDetailsFragment_to_addWorkDetailsFragment, bundle)
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderSpinner.adapter = adapter
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


    }
}