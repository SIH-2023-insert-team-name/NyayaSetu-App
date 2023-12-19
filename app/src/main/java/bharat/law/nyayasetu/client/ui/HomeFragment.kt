package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.adapter.ImageAdapter
import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import bharat.law.nyayasetu.databinding.FragmentLawyerAppointmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private val handler = Handler(Looper.getMainLooper())
    private val imageChangeRunnable = object : Runnable {
        override fun run() {
            viewPager.currentItem = viewPager.currentItem + 1
            handler.postDelayed(this, 4000)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewPager = binding.viewPagerImages


        val images = listOf(
            R.drawable.image_1, // Replace with your image resources
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4
        )

        val adapter = ImageAdapter(images, viewPager)
        binding.viewPagerImages.adapter = adapter
        handler.postDelayed(imageChangeRunnable, 4000)

        binding.cvLawyerAppointment.setOnClickListener {

        findNavController().navigate(R.id.action_homeFragment_to_lawyerAppointmentFragment)
        }

        binding.cvLegalAssist.setOnClickListener{
//            findNavController().navigate(R.id.action_homeFragment_to_addOtherDetailsFragment)
        }

        binding.cvConsultancy.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_consultancyFragment)
        }

        binding.cvLegalAidClinic.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_legalAidClinicFragment)
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(imageChangeRunnable) // Stop auto-scroll when not visible
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(imageChangeRunnable, 4000) // Resume auto-scroll
    }

}