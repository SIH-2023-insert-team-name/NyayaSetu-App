package bharat.law.nyayasetu.lawyer.ui_onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentAddOtherDetailsBinding
import bharat.law.nyayasetu.lawyer.activities.LawyerActivity
import bharat.law.nyayasetu.lawyer.activities.LawyerOnboardingActivity
import bharat.law.nyayasetu.models.AddDocWriterData
import bharat.law.nyayasetu.models.AddLawyerData
import bharat.law.nyayasetu.models.AddNotaryData
import bharat.law.nyayasetu.models.PersonalDetailsData
import bharat.law.nyayasetu.models.WorkDetailsData
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddOtherDetailsFragment : Fragment() {

    private var _binding: FragmentAddOtherDetailsBinding? = null
    val viewModel: LawyerViewModel by viewModels<LawyerViewModel>()
    var languagesSpoken: String? = null
    lateinit var authToken: String
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddOtherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!
        val lspType = arguments?.getString(Constants.LSP_TYPE)
        val personalDetails = arguments?.getParcelable<PersonalDetailsData>(Constants.PERSONAL_DETAILS)
        val workDetails = arguments?.getParcelable<WorkDetailsData>(Constants.WORK_DETAILS)

        var lawyerData = AddLawyerData()
        var notaryData = AddNotaryData()
        var docWriterData = AddDocWriterData()


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.indian_languages_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.languageSpinner.adapter = adapter
        }

        binding.languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val selectedLanguage = parent.getItemAtPosition(position).toString()
                    languagesSpoken = selectedLanguage
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        binding.btnNext.setOnClickListener {
            binding.progressBar4.isVisible = true
            when (lspType) {
                Constants.LAWYER -> {
                    lawyerData = AddLawyerData(
                        personalDetails?.aadhar,
                        personalDetails?.age,
                        workDetails?.availability,
                        workDetails?.bar_reg_np,
                        "",
                        binding.etCost.text.toString().toInt(),
                        workDetails?.document_url,
                        workDetails?.yoe,
                        personalDetails?.gender,
                        "",
                        languagesSpoken,
                        binding.etLocation.text.toString(),
                        personalDetails?.name,
                        0,
                        " ",
                        5.0,
                        binding.etSummary.text.toString()
                    )
                    viewModel.addLawyer(authToken, lawyerData)
                }
                Constants.NOTARY -> {
                    notaryData = AddNotaryData(
                        personalDetails?.aadhar,
                        personalDetails?.age,
                        workDetails?.availability,
                        workDetails?.bar_reg_np,
                        workDetails?.comm_expiry_date,
                        workDetails?.notary_comm_no,
                        binding.etCost.text.toString().toInt(),
                        workDetails?.document_url,
                        workDetails?.yoe,
                        personalDetails?.gender,
                        "",
                        workDetails?.jurisdiction_area,
                        languagesSpoken,
                        binding.etLocation.text.toString(),
                        personalDetails?.name,
                        0,
                        " ",
                        5,
                        binding.etSummary.text.toString()
                    )
                    viewModel.addNotary(authToken, notaryData)
                }
                Constants.DOCWRITER -> {
                    docWriterData = AddDocWriterData(
                        personalDetails?.aadhar,
                        personalDetails?.age,
                        workDetails?.availability,
                        binding.etCost.text.toString().toDouble(),
                        workDetails?.document_url,
                        workDetails?.yoe,
                        personalDetails?.gender,
                        "",
                        languagesSpoken,
                        binding.etLocation.text.toString(),
                        personalDetails?.name,
                        0,
                        " ",
                        5.0,
                        binding.etSummary.text.toString()
                    )
                    viewModel.addDocWriter(authToken, docWriterData)
                }
            }
        }

    }

    private fun attachObservers() {
        viewModel.addLawyerResponse.observe(viewLifecycleOwner, Observer {
            val data = it.body()
            if (data?.message == Constants.SUCCESSFULLY_REGISTERED){
                binding.progressBar4.isVisible = false
                AppSession(requireContext()).put(Constants.IS_LSP_ONBOARDING_DONE, true)
                Toast.makeText(requireContext(), Constants.SUCCESSFULLY_REGISTERED, Toast.LENGTH_SHORT).show()
                goToLawyerDashboard()
            } else {
                Toast.makeText(requireContext(), Constants.OOPS_SW, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.addDocWriterResponse.observe(viewLifecycleOwner, Observer {

            when(it.code()){
                Constants.CODE_200->{
                    val data = it.body()
                    if (data?.message == Constants.SUCCESSFULLY_REGISTERED) {
                        binding.progressBar4.isVisible = false
                        AppSession(requireContext()).put(Constants.IS_LSP_ONBOARDING_DONE, true)
                        Toast.makeText(
                            requireContext(),
                            Constants.SUCCESSFULLY_REGISTERED,
                            Toast.LENGTH_SHORT
                        ).show()
                        goToLawyerDashboard()
                    } else {
                        Toast.makeText(requireContext(), Constants.OOPS_SW, Toast.LENGTH_SHORT).show()
                    }
                }
                Constants.ERR_201->{
                    showErrorCodeLog(Constants.ERR_201, it.message())
                }
                Constants.ERR_400->{
                    showErrorCodeLog(Constants.ERR_400, it.message())
                }
                Constants.ERR_401->{
                    showErrorCodeLog(Constants.ERR_401, it.message())
                }
                Constants.ERR_402->{
                    showErrorCodeLog(Constants.ERR_402, it.message())
                }
                Constants.ERR_403->{
                    showErrorCodeLog(Constants.ERR_403, it.message())
                }
                Constants.ERR_404->{
                    showErrorCodeLog(Constants.ERR_404, it.message())
                }
                Constants.ERR_500->{
                    showErrorCodeLog(Constants.ERR_500, it.message())
                }
                Constants.ERR_503->{
                    showErrorCodeLog(Constants.ERR_503, it.message())
                }
            }

        })

        viewModel.addNotaryResponse.observe(viewLifecycleOwner, Observer {
            val data = it.body()
            if (data?.message == Constants.SUCCESSFULLY_REGISTERED){
                binding.progressBar4.isVisible = false
                AppSession(requireContext()).put(Constants.IS_LSP_ONBOARDING_DONE, true)
                Toast.makeText(requireContext(), Constants.SUCCESSFULLY_REGISTERED, Toast.LENGTH_SHORT).show()
                goToLawyerDashboard()
            } else {
                Toast.makeText(requireContext(), Constants.OOPS_SW, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun goToLawyerDashboard() {
        val intent = Intent(requireContext(), LawyerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun showErrorCodeLog(errCode: Int?, errMsg: String?){
        Log.d("CODE_ERROR_NYAYA", "Error Code: $errCode, Error Msg: $errMsg")
    }

}