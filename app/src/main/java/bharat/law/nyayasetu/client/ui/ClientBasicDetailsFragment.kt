package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentClientBasicBinding
import bharat.law.nyayasetu.databinding.FragmentClientPersonalDetailsBinding
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.ClientPersonalDetailsData
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientBasicDetailsFragment : Fragment() {

    private var _binding: FragmentClientBasicBinding? = null
    private val lawyerViewModel: LawyerViewModel by viewModels()
    var selectedAvailability: String? = null
    lateinit var authToken: String
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentClientBasicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!
        val personalDetails = arguments?.getParcelable<ClientPersonalDetailsData>(Constants.CLIENT_PERSONAL_DETAILS)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.availability_array_alt,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.availabilitySpinner.adapter = adapter
        }


        binding.btnNext.setOnClickListener {
            val addClientData = AddUserData(
                aadhar = personalDetails?.aadhar.toString(),
                age = personalDetails?.age.toString(),
                availability = selectedAvailability.toString(),
                budget = binding.etBudget.text.toString().toInt(),
                city = personalDetails?.city.toString(),
                experience = binding.etExperience.text.toString().toInt(),
                gender = personalDetails?.gender.toString(),
                legal_needs = binding.etLegalNeeds.text.toString(),
                name = personalDetails?.name.toString(),
                preferred_language = personalDetails?.preferredLanguages.toString()
            )
            lawyerViewModel.addClient("Bearer $authToken", addClientData)
        }

        binding.availabilitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    selectedAvailability = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        lawyerViewModel.addClientResponse.observe(viewLifecycleOwner, Observer {
            if (it.code() == Constants.CODE_200){
                val data = it.body()
                if (data?.message == Constants.SUCCESSFULLY_REGISTERED){
                    findNavController().navigate(R.id.action_clientBasicFragment_to_homeFragment)
                }
            }
        })
    }
}