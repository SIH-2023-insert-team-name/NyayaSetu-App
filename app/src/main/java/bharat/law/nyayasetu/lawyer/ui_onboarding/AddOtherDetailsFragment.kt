package bharat.law.nyayasetu.lawyer.ui_onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentAddOtherDetailsBinding
import bharat.law.nyayasetu.lawyer.activities.LawyerActivity
import bharat.law.nyayasetu.lawyer.activities.LawyerOnboardingActivity
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddOtherDetailsFragment : Fragment() {

    private var _binding: FragmentAddOtherDetailsBinding? = null
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

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.indian_languages_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.languageSpinner.adapter = adapter
        }

        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedLanguage = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.btnNext.setOnClickListener {
            goToLawyerDashboard()
        }
    }

    private fun goToLawyerDashboard() {
        AppSession(requireContext()).put(Constants.IS_LSP_ONBOARDING_DONE, true)
        val intent = Intent(requireContext(), LawyerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}