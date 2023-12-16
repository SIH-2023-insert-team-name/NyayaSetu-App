package bharat.law.nyayasetu.lawyer

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import bharat.law.nyayasetu.databinding.FragmentLSPSelectionBinding
import bharat.law.nyayasetu.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LSPSelectionFragment : Fragment() {

    private var _binding: FragmentLSPSelectionBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLSPSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedOption = ""

        binding.apply {
            btnNext.setOnClickListener{
                when(selectedOption){
                    Constants.LAWYER, Constants.NOTARY, Constants.DOCWRITER -> {
                        val bundle = Bundle()
                        bundle.putString("lspTpe", selectedOption)

                        val fragment = AddPersonalDetailsFragment()
                        fragment.arguments = bundle
                        findNavController().navigate(R.id.action_LSPSelectionFragment2_to_addPersonalDetailsFragment, bundle)
                    }
                }
            }
            cvLawyer.setOnClickListener {
                selectedOption = Constants.LAWYER
                clLawyer.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.material_blue))
                clNotary.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                clDocumentWriter.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                tvLawyer.setTextColor(Color.WHITE)
                tvNotary.setTextColor(Color.BLACK)
                tvDocumentWriter.setTextColor(Color.BLACK)
                ivLawyer.setImageResource(R.drawable.ic_person_white)
                ivNotary.setImageResource(R.drawable.ic_person)
                ivDocumentWriter.setImageResource(R.drawable.ic_person)
            }

            cvNotary.setOnClickListener {
                selectedOption = Constants.NOTARY
                clLawyer.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                clNotary.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.material_blue))
                clDocumentWriter.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                tvNotary.setTextColor(Color.WHITE)
                tvLawyer.setTextColor(Color.BLACK)
                tvDocumentWriter.setTextColor(Color.BLACK)
                ivLawyer.setImageResource(R.drawable.ic_person)
                ivNotary.setImageResource(R.drawable.ic_person_white)
                ivDocumentWriter.setImageResource(R.drawable.ic_person)
            }

            cvDocumentWriter.setOnClickListener {
                selectedOption = Constants.DOCWRITER
                clLawyer.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                clNotary.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                clDocumentWriter.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.material_blue))
                tvDocumentWriter.setTextColor(Color.WHITE)
                tvNotary.setTextColor(Color.BLACK)
                tvLawyer.setTextColor(Color.BLACK)
                ivLawyer.setImageResource(R.drawable.ic_person)
                ivNotary.setImageResource(R.drawable.ic_person)
                ivDocumentWriter.setImageResource(R.drawable.ic_person_white)
            }
        }
    }



}