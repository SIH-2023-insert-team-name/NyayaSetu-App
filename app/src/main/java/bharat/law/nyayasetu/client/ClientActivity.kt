package bharat.law.nyayasetu.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.ActivityClientBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientActivity : AppCompatActivity() {
    lateinit var binding:ActivityClientBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientBinding.inflate(layoutInflater)
        window.setStatusBarColor(ContextCompat.getColor(baseContext, R.color.light_grey))

//        val isOnboard = intent.getBooleanExtra("isOnboard", false)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
//        val navGraph = navController.navInflater.inflate(R.navigation.client_nav_graph)
//        if (isOnboard){
//            navGraph.setStartDestination(R.id.homeFragment)
//        } else {
//            navGraph.setStartDestination(R.id.clientPersonalDetailsFragment)
//        }

        setContentView(binding.root)

    }
}