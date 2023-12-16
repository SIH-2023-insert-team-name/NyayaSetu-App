package bharat.law.nyayasetu.lawyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentAddPersonalDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonalDetailsFragment : Fragment() {

    private var _binding: FragmentAddPersonalDetailsBinding? = null

    val args: AddPersonalDetailsFragmentArgs by navArgs()
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPersonalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("lspTpe", args.lspType)

            val fragment = AddWorkDetailsFragment()
            fragment.arguments = bundle
            findNavController().navigate(R.id.action_addPersonalDetailsFragment_to_addWorkDetailsFragment, bundle)
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderSpinner.adapter = adapter
        }

        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedGender = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }
}