package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.LayoutDialogFragmentBinding
import bharat.law.nyayasetu.models.GetLawyersResponse

class LawyerDialogFragment(val lawyerData: GetLawyersResponse) : DialogFragment() {

    private var _binding: LayoutDialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutDialogFragmentBinding.inflate(inflater, container, false)

        binding.name.text ="Name: " + lawyerData.name
        binding.age.text = "Age: "+lawyerData.age.toString()
        binding.rating.text = "Rating: "+lawyerData.rating.toString()
        binding.category.text = "Category: "+lawyerData.category
        binding.experience.text = "Experience: "+lawyerData.experience.toString()
        binding.gender.text = "Gender: "+lawyerData.gender

        binding.btnChat.setOnClickListener {
            val fragmentToNavigateTo = RegisterCaseFragment(lawyerData) // Replace with your actual Fragment class

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}