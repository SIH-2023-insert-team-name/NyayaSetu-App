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



    private val lawyerViewModel: LawyerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLawyerAppointmentBinding.inflate(inflater, container, false)

//        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!

        lawyerViewModel.getLSP()

        lawyerViewModel.getLSPResponse.observe(viewLifecycleOwner, Observer {
            val lawyerList = it.body() as ArrayList<GetLawyersResponse>

            binding.cvBankruptcy.setOnClickListener {
                val bankruptcyList = filterList(Constants.BANKRUPTCY_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvCorporate.setOnClickListener {
                val corporateList = filterList(Constants.CORPORATE_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvConstitutional.setOnClickListener {
                val constitutionalList = filterList(Constants.CONSTITUTIONAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvCriminal.setOnClickListener {
                val criminalList = filterList(Constants.CRIMINAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvEmployment.setOnClickListener {
                val employmentList = filterList(Constants.EMPLOYMENT_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvEntertainment.setOnClickListener {
                val entertainmentList = filterList(Constants.ENTERTAINMENT_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvEstate.setOnClickListener {
                val estateList = filterList(Constants.ESTATE_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvFamily.setOnClickListener {
                val familyList = filterList(Constants.FAMILY_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvImmigration.setOnClickListener {
                val immigrationList = filterList(Constants.IMMIGRATION_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvIntellectual.setOnClickListener {
                val intellectualList = filterList(Constants.INTELLECTUAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvPersonal.setOnClickListener {
                val personalList = filterList(Constants.PERSONAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
            binding.cvTax.setOnClickListener {
                val taxList = filterList(Constants.TAX_CATEGORY,lawyerList)
                setupBottomSheetDialog(lawyerList)
            }
        })



        return binding.root
    }

    private fun filterList(
        category: String,
        lList:ArrayList<GetLawyersResponse>
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