package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import bharat.law.nyayasetu.adapter.ConsultancyAdapter
import bharat.law.nyayasetu.databinding.FragmentConsultancyBinding
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class ConsultancyFragment : Fragment() {
    private var _binding: FragmentConsultancyBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var consultancyAdapter: ConsultancyAdapter
    lateinit var authToken:String
    val lawyerViewmodel : LawyerViewModel by viewModels()
    var lData = ArrayList<GetLawyersResponse>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsultancyBinding.inflate(inflater, container, false)
        authToken = AppSession(requireContext()).getString(Constants.AUTH_TOKEN)!!
        lawyerViewmodel.getLSP(authToken)

        lawyerViewmodel.getLSPResponse.observe(viewLifecycleOwner, Observer{
            lData = it.body() as ArrayList<GetLawyersResponse>

            val adapter = ConsultancyAdapter(requireContext(),
               lData
            )

            adapter.notifyDataSetChanged()
            binding.rvConsutancy.setHasFixedSize(true)
            binding.rvConsutancy.adapter = adapter
            binding.rvConsutancy.layoutManager = GridLayoutManager(context,1)
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }

}