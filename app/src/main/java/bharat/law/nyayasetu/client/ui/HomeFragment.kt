package bharat.law.nyayasetu.client.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.adapter.ImageAdapter
import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

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

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val balloon = Balloon.Builder(requireContext())
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText("Edit your profile here!")
            .setTextColorResource(R.color.white)
            .setTextSize(13f)
            .setAutoDismissDuration(5000L)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(12)
            .setCornerRadius(8f)
            .setBackgroundColorResource(R.color.black_tile)
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()

       binding.cvLawyerAppointment.showAlignBottom(balloon)

        binding.hamBurger.setOnClickListener{
            val dialog = LanguageBottomSheet()
            dialog.show(parentFragmentManager,"Dialog")
        }


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
        binding.cvLegalAssist.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatBotFragment)
        }
        binding.cvReview.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recommendationFragment)
        }

       binding.cvNGO.setOnClickListener {
           findNavController().navigate(R.id.action_homeFragment_to_ngoFragment)
       }

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