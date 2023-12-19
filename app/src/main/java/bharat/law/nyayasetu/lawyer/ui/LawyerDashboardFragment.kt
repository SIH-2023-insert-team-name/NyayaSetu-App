package bharat.law.nyayasetu.lawyer.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.adapter.ImageAdapter
import bharat.law.nyayasetu.databinding.FragmentHomeBinding
import bharat.law.nyayasetu.databinding.FragmentLawyerDashboardBinding


class LawyerDashboardFragment : Fragment() {

    private var _binding: FragmentLawyerDashboardBinding? = null
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLawyerDashboardBinding.inflate(inflater, container, false)

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