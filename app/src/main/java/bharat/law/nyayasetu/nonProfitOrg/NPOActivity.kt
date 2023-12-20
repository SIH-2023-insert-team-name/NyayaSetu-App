package bharat.law.nyayasetu.nonProfitOrg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.ActivityClientBinding
import bharat.law.nyayasetu.databinding.ActivityNpoactivityBinding

class NPOActivity : AppCompatActivity() {
    lateinit var binding: ActivityNpoactivityBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNpoactivityBinding.inflate(layoutInflater)
        window.setStatusBarColor(ContextCompat.getColor(baseContext, R.color.light_grey))

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        setContentView(binding.root)
    }
}