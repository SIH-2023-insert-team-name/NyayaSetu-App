package bharat.law.nyayasetu.client

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.ActivityClientBinding
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class ClientActivity : AppCompatActivity() {
    lateinit var binding:ActivityClientBinding
    private lateinit var navController: NavController
    var lang: String? = null
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

        lang = AppSession(this).getString(Constants.SAVED_LANG)
        if (lang != null){
            setLangauge(this, lang!!)
        } else {
            setLangauge(this, "en")
        }

        setContentView(binding.root)

    }

    fun restartFragment(fragmentId: Int) {
        val currentFragment = this.supportFragmentManager.findFragmentById(fragmentId)!!

        this.supportFragmentManager.beginTransaction()
            .detach(currentFragment)
            .commit()
        this.supportFragmentManager.beginTransaction()
            .attach(currentFragment)
            .commit()
    }

    private fun setLangauge(activity: Activity, language: String){
        val locale = Locale(language)
        val resources = activity.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration,resources.displayMetrics)
    }
}