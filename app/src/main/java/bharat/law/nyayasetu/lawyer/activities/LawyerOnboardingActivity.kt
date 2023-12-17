package bharat.law.nyayasetu.lawyer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.ActivityLawyerBinding
import bharat.law.nyayasetu.databinding.ActivityLawyerOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LawyerOnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLawyerOnboardingBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLawyerOnboardingBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentLawyer) as NavHostFragment
        navController = navHostFragment.navController

        setContentView(binding.root)

    }
}