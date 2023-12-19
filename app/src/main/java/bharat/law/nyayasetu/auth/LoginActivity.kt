package bharat.law.nyayasetu.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.client.ClientActivity
import bharat.law.nyayasetu.databinding.ActivityLoginBinding
import bharat.law.nyayasetu.lawyer.activities.LawyerActivity
import bharat.law.nyayasetu.lawyer.activities.LawyerOnboardingActivity
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val lawyerViewModel: LawyerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setStatusBarColor(ContextCompat.getColor(baseContext, R.color.light_grey))


        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.btnSignIn.setOnClickListener {
            binding.progressBar.isVisible = true
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                val authUserData = AuthUserData(
                    email,
                    pass
                )
                lawyerViewModel.authUser(authUserData)
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }

        lawyerViewModel.authResponse.observe(this, Observer {
            if (it.body()?.message == Constants.AUTH_SUCCESS) {
                binding.progressBar.isVisible = false
                val isLSP = it.body()?.isLSP
                val isOnboard = it.body()?.isOnboard
                AppSession(this).putString(Constants.LSP_STRING, it.body()?.isLSP.toString())
                AppSession(this).putString(Constants.AUTH_TOKEN, it.body()?.token)

                if (isLSP == 1 && isOnboard == true) {
                    goToLawyerDashboard()
                } else if (isLSP ==1 && isOnboard == false) {
                    goToLawyer()
                } else {
                    goToClient(isOnboard)
                }
            }
        })
    }

    private fun goToLawyer() {
        AppSession(this).put(Constants.IS_LOGIN, true)
        AppSession(this).put(Constants.IS_LSP, true)
        val intent = Intent(this, LawyerOnboardingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun goToLawyerDashboard() {
        AppSession(this).put(Constants.IS_LOGIN, true)
        AppSession(this).put(Constants.IS_LSP, true)
        val intent = Intent(this, LawyerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun goToClient(isOnboard: Boolean?) {
        AppSession(this).put(Constants.IS_LOGIN, true)
        AppSession(this).put(Constants.IS_LSP, false)
        val intent = Intent(this, ClientActivity::class.java)
        intent.putExtra("isOnboard", isOnboard)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}