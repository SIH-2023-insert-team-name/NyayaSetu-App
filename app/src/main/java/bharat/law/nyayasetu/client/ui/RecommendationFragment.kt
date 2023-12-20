package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.adapter.ConsultancyAdapter
import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import bharat.law.nyayasetu.databinding.FragmentRecommendationBinding
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.viewmodels.LawyerViewModel

class RecommendationFragment : Fragment() {
    private lateinit var lawyerList: MutableList<GetLawyersResponse>
    private val lawyerViewModel: LawyerViewModel by viewModels()
    private var _binding: FragmentRecommendationBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var consultancyAdapter: ConsultancyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        lawyerViewModel.getRecommendedLawyer()
        lawyerViewModel.getLSPResponse.observe(viewLifecycleOwner, Observer {
            lawyerList = it.body()!! as ArrayList<GetLawyersResponse>
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        setupRecyclerView()


        consultancyAdapter.onItemClick = {

            val dialog = LawyerDialogFragment(it)
            dialog.show(parentFragmentManager,"Dialog")
//            val fragmentToNavigateTo = ChatFragment() // Replace with your actual Fragment class
//
//            val fragmentManager = requireActivity().supportFragmentManager // Use requireActivity() if you are inside a Fragment, or getActivity() if inside an Activity
//            val transaction = fragmentManager.beginTransaction()
//            transaction.replace(R.id.navHostFragment, fragmentToNavigateTo) // R.id.fragment_container is the ID of the container where you want to replace the Fragment
//            transaction.addToBackStack(null) // Th    is allows the user to navigate back to the previous Fragment
//            transaction.commit()

        }
    }

    private fun onClick() {

    }

    private fun setupRecyclerView() {

        consultancyAdapter = ConsultancyAdapter(requireContext(), lawyerList)
        binding.rvBottomSheet.setHasFixedSize(true)
        binding.rvBottomSheet.adapter = consultancyAdapter
        consultancyAdapter.notifyDataSetChanged()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        binding.rvBottomSheet.layoutManager = linearLayoutManager
    }
}