package bharat.law.nyayasetu.client.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.client.ClientActivity
import bharat.law.nyayasetu.databinding.BottomSheetDialogFragmentBinding
import bharat.law.nyayasetu.databinding.LangaugeBottomSheetBinding
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class LanguageBottomSheet: BottomSheetDialogFragment() {

    lateinit var binding: LangaugeBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val chatInflater = LayoutInflater.from(requireContext())
        binding = LangaugeBottomSheetBinding.inflate(chatInflater)

        val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
        val result = when (selectedRadioButtonId) {
            R.id.radioButtonBn -> 0
            R.id.radioButtonHi -> 1
            R.id.radioButtonEn -> 2
            else -> -1 // No radio button selected
        }

        binding.btnSignUp.setOnClickListener {
            when (result) {
                0 -> {
                    setLangauge(requireActivity(),"bn")
                    AppSession(requireContext()).putString(Constants.SAVED_LANG, "bn")
                }
                1 -> {
                    setLangauge(requireActivity(),"hi")
                    AppSession(requireContext()).putString(Constants.SAVED_LANG, "hi")
                }
                2 -> {
                    setLangauge(requireActivity(),"en")
                    AppSession(requireContext()).putString(Constants.SAVED_LANG, "en")
                }
                else -> {
                    setLangauge(requireActivity(),"en")
                    AppSession(requireContext()).putString(Constants.SAVED_LANG, "en")
                }
            }
            (requireActivity() as ClientActivity).restartFragment(R.id.homeFragment)
            dismiss()
        }

        return binding.root
    }

    private fun setLangauge(activity: Activity, language: String){
        val locale = Locale(language)
        val resources = activity.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration,resources.displayMetrics)
    }
}