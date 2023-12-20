package bharat.law.nyayasetu.nonProfitOrg

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.adapter.ImageAdapter
import bharat.law.nyayasetu.databinding.FragmentLawyerDashboardBinding
import bharat.law.nyayasetu.databinding.FragmentNgoDashboardBinding

class NgoDashboardFragment : Fragment() {
    private var _binding: FragmentNgoDashboardBinding? = null
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
        _binding = FragmentNgoDashboardBinding.inflate(inflater, container, false)

        viewPager = binding.viewPagerImages


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = listOf(
            R.drawable.banner_1, // Replace with your image resources
            R.drawable.banner_2,
            R.drawable.banner_3,
        )

        binding.cvProBono.setOnClickListener {
            findNavController().navigate(R.id.action_ngoDashboardFragment_to_ngoFragment2)
        }

        binding.cvCommunityForum.setOnClickListener {
            findNavController().navigate(R.id.action_ngoDashboardFragment_to_communityForumFragment)
        }

        binding.cvPartnership.setOnClickListener {
            findNavController().navigate(R.id.action_ngoDashboardFragment_to_partnershipFragment)
        }

        val adapter = ImageAdapter(images, viewPager)
        binding.viewPagerImages.adapter = adapter
        handler.postDelayed(imageChangeRunnable, 4000)
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