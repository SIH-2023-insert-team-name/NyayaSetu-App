package bharat.law.nyayasetu.client.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import bharat.law.nyayasetu.adapter.ConsultancyAdapter
import bharat.law.nyayasetu.databinding.BottomSheetDialogFragmentBinding
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogFragment(val lawyerList: MutableList<GetLawyersResponse>): BottomSheetDialogFragment() {
    lateinit var binding:BottomSheetDialogFragmentBinding
    lateinit var authToken:String
    private lateinit var consultancyAdapter: ConsultancyAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val chatInflater = LayoutInflater.from(requireContext())
        binding = BottomSheetDialogFragmentBinding.inflate(chatInflater)

        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!
        setupFullHeight()
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
        return binding.root
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

    private fun setupFullHeight() {
        val bottomSheetDialog = dialog as BottomSheetDialog
        val behavior = bottomSheetDialog.behavior
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.isDraggable = false
    }
}