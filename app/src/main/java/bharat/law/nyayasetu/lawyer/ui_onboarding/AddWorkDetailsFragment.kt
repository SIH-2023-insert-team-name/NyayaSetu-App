package bharat.law.nyayasetu.lawyer.ui_onboarding

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentAddWorkDetailsBinding
import bharat.law.nyayasetu.models.PersonalDetailsData
import bharat.law.nyayasetu.models.WorkDetailsData
import bharat.law.nyayasetu.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.Calendar

@AndroidEntryPoint
class AddWorkDetailsFragment : Fragment() {

    private var _binding: FragmentAddWorkDetailsBinding? = null
    lateinit var datePickerDialog: DatePickerDialog
    private var uploadUri: Uri? = null
    var selectedAvailability: String? = null
    var selectedCategory: String? = null

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
        val lspType = arguments?.getString(Constants.LSP_TYPE)
        val personalDetails = arguments?.getParcelable<PersonalDetailsData>(Constants.PERSONAL_DETAILS)
        when (lspType) {
            Constants.LAWYER -> {
                binding.groupNotary.isVisible = false
                binding.llBarNumber.isVisible = true
                binding.llCategory.isVisible = true
            }

            Constants.NOTARY -> {
                binding.groupNotary.isVisible = true
            }

            Constants.DOCWRITER -> {
                binding.llBarNumber.isVisible = false
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.availability_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.availabilitySpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lawyer_category,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }

        binding.etlCommissionExpiry.setOnClickListener {
            showDatePickerDialog()
        }
        binding.etCommissionExpriy.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnNext.setOnClickListener {
            var workDetailsData = WorkDetailsData()
            if (lspType == Constants.LAWYER) {
                workDetailsData = WorkDetailsData(
                    binding.etBarNumber.text.toString(),
                    binding.etExperience.text.toString().toInt(),
                    selectedAvailability,
                    selectedCategory,
                    uploadUri.toString(),
                    " ",
                    " ",
                    " "
                )
            } else if (lspType == Constants.NOTARY) {
                workDetailsData = WorkDetailsData(
                    binding.etBarNumber.text.toString(),
                    binding.etExperience.text.toString().toInt(),
                    selectedAvailability,
                    "",
                    uploadUri.toString(),
                    binding.etCommissionNumber.text.toString(),
                    formatDateString(binding.etCommissionExpriy.text.toString()),
                    binding.etJurisdictionCovered.text.toString()
                )
            } else if (lspType == Constants.DOCWRITER) {
                workDetailsData = WorkDetailsData(
                    " ",
                    binding.etExperience.text.toString().toInt(),
                    selectedAvailability,
                    "",
                    uploadUri.toString(),
                    " ",
                    " ",
                    " "
                )
            }
            val bundle = Bundle()
            bundle.putString(Constants.LSP_TYPE, lspType)
            bundle.putParcelable(Constants.PERSONAL_DETAILS, personalDetails)
            bundle.putParcelable(Constants.WORK_DETAILS, workDetailsData)

            val fragment = AddOtherDetailsFragment()
            fragment.arguments = bundle
            findNavController().navigate(
                R.id.action_addWorkDetailsFragment_to_addOtherDetailsFragment,
                bundle
            )
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

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    selectedCategory = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        binding.btnUploadFile.setOnClickListener {
            chooseFile()
        }

    }

    private fun chooseFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 100)
        } catch (e: Exception) {
            Log.d("test-log", "Something went wrong")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && data != null) {
            val uri: Uri? = data.data
            val path: String = uri?.path.toString()
            val file = File(path)
            binding.tvFileName.text = file.name

            if (uri != null) {
                uploadUri = uri
//                uploadFile(uri)
            } else {
                Toast.makeText(requireContext(), "NO FILE", Toast.LENGTH_SHORT).show()
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        datePickerDialog = DatePickerDialog(
            requireContext(), R.style.DatePickerDialogTheme,
            { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                binding.etCommissionExpriy.text = "${dayOfMonth}/${monthOfYear + 1}/${year}"

                removeError()

            }, year, month, day
        )

        datePickerDialog.show()

    }

    private fun removeError(){
        binding.etCommissionExpriy.error = null
    }

    fun formatDateString(date: String): String {
        val parts = date.split("/")

        if (parts.size != 3) {
            // Invalid date format, return the original string
            return date
        }

        val day = parts[0]
        val month = parts[1]
        val year = parts[2]

        val formattedDay = if (day.length == 1) "0$day" else day
        val formattedMonth = if (month.length == 1) "0$month" else month

        return "$formattedDay/$formattedMonth/$year"
    }

}