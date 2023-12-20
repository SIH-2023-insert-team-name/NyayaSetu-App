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
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
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

        val jsonString = """ 
     [
    {
        "_id": "65812ac7c5e8043e85d372c5",
        "name": "Sonia K",
        "aadhar": "374-524789847-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "family",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:31:51.724Z",
        "updatedAt": "2023-12-19T05:31:51.724Z",
        "__v": 0
    },
    {
        "_id": "65812ba4e31e1441ced79edf",
        "name": "Sonia K",
        "aadhar": "374-52478-9847-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "immigration",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:35:32.615Z",
        "updatedAt": "2023-12-19T05:35:32.615Z",
        "__v": 0
    },
    {
        "_id": "65812ed33bfcdd1081eb6a1b",
        "name": "kleshab K",
        "email": "keshab0101@gmail.com",
        "aadhar": "374-52478-94700-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "estate planning",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:49:07.369Z",
        "updatedAt": "2023-12-19T05:49:07.369Z",
        "__v": 0
    },
    {
        "_id": "65812fa290a7389e1e83a39b",
        "name": "kleshab K",
        "email": "keshab0101@gmail.com",
        "aadhar": "374-52478-904700-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "constitutional",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:52:34.551Z",
        "updatedAt": "2023-12-19T05:52:34.551Z",
        "__v": 0
    },
    {
        "_id": "65812ed33bfcdd1081eb6a1b",
        "name": "kleshab K",
        "email": "keshab0101@gmail.com",
        "aadhar": "374-52478-94700-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "constitutional",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:49:07.369Z",
        "updatedAt": "2023-12-19T05:49:07.369Z",
        "__v": 0
    },
    {
        "_id": "65812fa290a7389e1e83a39b",
        "name": "kleshab K",
        "email": "keshab0101@gmail.com",
        "aadhar": "374-52478-904700-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "criminal",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:52:34.551Z",
        "updatedAt": "2023-12-19T05:52:34.551Z",
        "__v": 0
    },
    {
        "_id": "65812ed33bfcdd1081eb6a1b",
        "name": "kleshab K",
        "email": "keshab0101@gmail.com",
        "aadhar": "374-52478-94700-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "criminal",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:49:07.369Z",
        "updatedAt": "2023-12-19T05:49:07.369Z",
        "__v": 0
    },
    {
        "_id": "65812fa290a7389e1e83a39b",
        "name": "kleshab K",
        "email": "keshab0101@gmail.com",
        "aadhar": "374-52478-904700-0783",
        "profile_pic": "https://placekitten.com/525/799",
        "bar_association_reg_no": "MP/3232/322",
        "gender": "male",
        "age": 43,
        "summary": "Artist arrive organization minute dinner wide middle couple.",
        "category": "business",
        "experience": 6,
        "location": "Delhi",
        "availability": "Full-time",
        "languages_spoken": "Hindi",
        "cost": 205,
        "points": 128,
        "incentive_level": "not applicable",
        "rating": 3.9,
        "document_url": "null",
        "createdAt": "2023-12-19T05:52:34.551Z",
        "updatedAt": "2023-12-19T05:52:34.551Z",
        "__v": 0
    },
]
"""

        val listType = object : TypeToken<ArrayList<GetLawyersResponse>>() {}.type
        val lawyerList: ArrayList<GetLawyersResponse> = Gson().fromJson(jsonString, listType)

//        lawyerViewModel.getLSPResponse.observe(viewLifecycleOwner, Observer {
//            val lawyerList = it.body() as ArrayList<GetLawyersResponse>

            binding.cvBankruptcy.setOnClickListener {
                val bankruptcyList = filterList(Constants.BANKRUPTCY_CATEGORY,lawyerList)
                setupBottomSheetDialog(bankruptcyList)
            }
            binding.cvCorporate.setOnClickListener {
                val corporateList = filterList(Constants.CORPORATE_CATEGORY,lawyerList)
                setupBottomSheetDialog(corporateList)
            }
            binding.cvConstitutional.setOnClickListener {
                val constitutionalList = filterList(Constants.CONSTITUTIONAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(constitutionalList)
            }
            binding.cvCriminal.setOnClickListener {
                val criminalList = filterList(Constants.CRIMINAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(criminalList)
            }
            binding.cvEmployment.setOnClickListener {
                val employmentList = filterList(Constants.EMPLOYMENT_CATEGORY,lawyerList)
                setupBottomSheetDialog(employmentList)
            }
            binding.cvEntertainment.setOnClickListener {
                val entertainmentList = filterList(Constants.ENTERTAINMENT_CATEGORY,lawyerList)
                setupBottomSheetDialog(entertainmentList)
            }
            binding.cvEstate.setOnClickListener {
                val estateList = filterList(Constants.ESTATE_CATEGORY,lawyerList)
                setupBottomSheetDialog(estateList)
            }
            binding.cvFamily.setOnClickListener {
                val familyList = filterList(Constants.FAMILY_CATEGORY,lawyerList)
                setupBottomSheetDialog(familyList)
            }
            binding.cvImmigration.setOnClickListener {
                val immigrationList = filterList(Constants.IMMIGRATION_CATEGORY,lawyerList)
                setupBottomSheetDialog(immigrationList)
            }
            binding.cvIntellectual.setOnClickListener {
                val intellectualList = filterList(Constants.INTELLECTUAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(intellectualList)
            }
            binding.cvPersonal.setOnClickListener {
                val personalList = filterList(Constants.PERSONAL_CATEGORY,lawyerList)
                setupBottomSheetDialog(personalList)
            }
            binding.cvTax.setOnClickListener {
                val taxList = filterList(Constants.TAX_CATEGORY,lawyerList)
                setupBottomSheetDialog(taxList)
            }
//        })



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