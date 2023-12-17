package bharat.law.nyayasetu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.core.view.postDelayed
import bharat.law.nyayasetu.auth.LoginActivity
import bharat.law.nyayasetu.client.ClientActivity
import bharat.law.nyayasetu.databinding.ActivitySplashScreenBinding
import bharat.law.nyayasetu.lawyer.activities.LawyerActivity
import bharat.law.nyayasetu.lawyer.activities.LawyerOnboardingActivity
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.utils.CustomMediaController
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    private var isLogin: Boolean = false
    private var isLSP: Boolean = false
    private var isLSPOnboardingDone: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setStatusBarColor(ContextCompat.getColor(baseContext, R.color.yellow))
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        isLogin = AppSession(this).getBoolean(Constants.IS_LOGIN)
        isLSP = AppSession(this).getBoolean(Constants.IS_LSP)
        isLSPOnboardingDone = AppSession(this).getBoolean(Constants.IS_LSP_ONBOARDING_DONE)

        val videoView: VideoView = binding.videoView

        // Set the video path (adjust the resource name accordingly)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.splash_video
        videoView.setVideoPath(videoPath)

        val mediaController = CustomMediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            videoView.start()
        }

        binding.root.postDelayed(2500) {
            navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen() {
        val destinationClass: Class<out Any> = when {
            isLogin && isLSP && isLSPOnboardingDone -> LawyerActivity::class.java
            isLogin && isLSP && !isLSPOnboardingDone -> LawyerOnboardingActivity::class.java
            isLogin && !isLSP -> ClientActivity::class.java
            else -> LoginActivity::class.java
        }
        startActivity(Intent(this, destinationClass))
        finish()
    }
}