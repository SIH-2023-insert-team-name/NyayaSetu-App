package bharat.law.nyayasetu.lawyer.ui_onboarding

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
import bharat.law.nyayasetu.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

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
        val lspType = arguments?.getString("lspType")
        when(lspType){
            Constants.LAWYER -> {
                binding.groupNotary.isVisible = false
                binding.llBarNumber.isVisible = true
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

        binding.btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("lspTpe", lspType)

            val fragment = AddOtherDetailsFragment()
            fragment.arguments = bundle
            findNavController().navigate(R.id.action_addWorkDetailsFragment_to_addOtherDetailsFragment, bundle)
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

        binding.btnUploadFile.setOnClickListener{
            chooseFile()
        }

    }

    private fun uploadFile(uri: Uri){
        viewLifecycleOwner.lifecycleScope.launch {

            val filesDir = requireActivity().filesDir
            val file = File(filesDir,"document.pdf")
            val inputStream = requireActivity().contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            inputStream!!.copyTo(outputStream)

            val requestBody = file.asRequestBody("application/pdf".toMediaTypeOrNull())
            val paySlipBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

//            checkEligibilityViewModel.uploadPaySlip(
//                "Bearer ${getToken()}",
//                getAndroidDeviceID(),
//                paySlipBody
//            )
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
                uploadFile(uri)
            } else {
                Toast.makeText(requireContext(), "NO FILE", Toast.LENGTH_SHORT).show()
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}