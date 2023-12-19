package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentLawyerAppointmentBinding
import bharat.law.nyayasetu.databinding.FragmentRegisterCaseBinding
import bharat.law.nyayasetu.lawyer.ui_onboarding.AddWorkDetailsFragment
import bharat.law.nyayasetu.models.AddCaseData
import bharat.law.nyayasetu.models.ChatResponseItemData
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterCaseFragment(data: GetLawyersResponse) : Fragment() {


    private var _binding: FragmentRegisterCaseBinding? = null
    private val binding
        get() = _binding!!
    val lawyerData = data
    lateinit var authToken: String
    private val lawyerViewModel: LawyerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterCaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clientEmail: String? = AppSession(requireContext()).getString(Constants.CLIENT_EMAIL)
        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!

        binding.btnNext.setOnClickListener {
            val addCaseData = AddCaseData(
                Parties_involved = binding.etPartiesInvolved.text.toString(),
                clientEmail,
                binding.etCaseSummary.text.toString(),
                lawyerData.email,
                binding.etPrevActions.text.toString()
            )

            lawyerViewModel.addCase("Bearer $authToken", addCaseData)

        }

        lawyerViewModel.addCaseResponse.observe(viewLifecycleOwner, Observer {
            if (it.code() ==Constants.CODE_200){
                if (it?.body()?.message == Constants.SUCCESSFULLY_SAVED){
                    Toast.makeText(requireContext(), "Booked an Appointment", Toast.LENGTH_SHORT).show()
                    navigateToChats(lawyerData)
                }
            }
        })
    }

    private fun navigateToChats(data: GetLawyersResponse){
        val fragmentToNavigateTo = ChatScreenFragment(data) // Replace with your actual Fragment class

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