package bharat.law.nyayasetu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.view.postDelayed
import bharat.law.nyayasetu.auth.LoginActivity
import bharat.law.nyayasetu.auth.SignUpActivity
import bharat.law.nyayasetu.client.ClientActivity
import bharat.law.nyayasetu.databinding.ActivitySplashScreenBinding
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.utils.CustomMediaController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    private var isLogin: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        isLogin = AppSession(this).getBoolean(Constants.IS_LOGIN)

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
        val destinationClass = if (isLogin) ClientActivity::class.java else LoginActivity::class.java
        startActivity(Intent(this, destinationClass))
        finish()
    }
}