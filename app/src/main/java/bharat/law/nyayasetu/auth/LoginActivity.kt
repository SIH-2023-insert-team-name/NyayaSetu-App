package bharat.law.nyayasetu.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.auth.SignUpActivity
import bharat.law.nyayasetu.client.ClientActivity
import bharat.law.nyayasetu.databinding.ActivityLoginBinding
import bharat.law.nyayasetu.lawyer.LawyerActivity
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
                val isLSP = it.body()?.isLSP
                AppSession(this).putString(Constants.LSP_STRING, it.body()?.isLSP.toString())
                AppSession(this).putString(Constants.AUTH_TOKEN, it.body()?.token)

                if(isLSP == 1){
                    goToLawyer()
                } else {
                    goToClient()
                }
            }
        })
    }

    private fun goToLawyer() {
        val intent = Intent(this, LawyerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun goToClient() {
        AppSession(this).put(Constants.IS_LOGIN,true)
        val intent = Intent(this, ClientActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}