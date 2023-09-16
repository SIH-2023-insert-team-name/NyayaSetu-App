package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentLawyerAppointmentBinding
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class LawyerAppointmentFragment : Fragment() {
    private var _binding: FragmentLawyerAppointmentBinding? = null
    private val binding
        get() = _binding!!

    lateinit var authToken: String

    var lawyerList= ArrayList<GetLawyersResponse>()

    private val lawyerViewModel: LawyerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLawyerAppointmentBinding.inflate(inflater, container, false)

        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!

        lawyerViewModel.getLSP(authToken)

        lawyerViewModel.getLSPResponse.observe(viewLifecycleOwner, Observer {
            lawyerList = it.body()!! as ArrayList<GetLawyersResponse>

            binding.cvCriminal.setOnClickListener {
                val criminalList = filterList(Constants.CRIMINAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(criminalList)
            }
        })



        return binding.root
    }

    private fun filterList(
        category: String,
        lList:MutableList<GetLawyersResponse>
    ): MutableList<GetLawyersResponse> {
        val filteredList = ArrayList<GetLawyersResponse>()

        for (i in lList) {
            if (i.category == category) {
                filteredList.add(i)
            }
        }
        return filteredList
    }
     fun setupBottomSheetDialog(list: MutableList<GetLawyersResponse>) {
        val dialog = BottomSheetDialogFragment(list)
        dialog.isCancelable = true
        dialog.show(parentFragmentManager, "BottomSheet")
    }

}