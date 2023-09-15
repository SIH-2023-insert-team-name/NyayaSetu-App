package bharat.law.nyayasetu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.postDelayed
import bharat.law.nyayasetu.client.ClientActivity
import bharat.law.nyayasetu.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding.root.postDelayed(2500) {
                startActivity(Intent(this, ClientActivity::class.java))
                finish()

        }
    }
}