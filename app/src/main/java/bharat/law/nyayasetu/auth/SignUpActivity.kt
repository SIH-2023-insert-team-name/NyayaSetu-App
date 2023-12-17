package bharat.law.nyayasetu.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import bharat.law.nyayasetu.MainActivity
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.ActivitySignUpBinding
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.LawyerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    var isLSP = 0
    private lateinit var email:String

    private val lawyerViewModel: LawyerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setStatusBarColor(ContextCompat.getColor(baseContext, R.color.light_grey))


        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        binding.btnSignUp.setOnClickListener {
            email = binding.etEmail.text.toString()
            val name = binding.etName.text.toString()
            val pass = binding.etPassword.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            val result = when (selectedRadioButtonId) {
                R.id.radioButtonYes -> 1
                R.id.radioButtonNo -> 0
                else -> -1 // No radio button selected
            }

            if (result!=-1){
               if (result==1){
                   isLSP=1
               } else {
                   isLSP=0
               }
            } else {
                Toast.makeText(this, "No Option Selected", Toast.LENGTH_SHORT).show()
            }


            val sharedPref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
            val editor2 = sharedPref.edit()
            editor2.putBoolean("hasLoggedIn", true)
            editor2.apply()


            if (email.isNotEmpty() && name.isNotEmpty() && confirmPass.isNotEmpty() && pass.equals(
                    confirmPass
                ) && pass.isNotEmpty()
            ) {
                if (isLSP ==1){
                    val registerData = RegisterData(
                        name,
                        email,
                        pass,
                        isLSP
                    )
                    lawyerViewModel.registerUser(registerData)

                } else if (isLSP == 0) {
                    val registerData = RegisterData(
                        name,
                        email,
                        pass,
                        isLSP
                    )
                    lawyerViewModel.registerUser(registerData)

                }
                AppSession(this).putString(Constants.NAME, name)

            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

        lawyerViewModel.registerResponse.observe(this, Observer {
            if (it.body()?.message == Constants.REGISTER_SUCCESS) {
                goToLogin()
            }
        })
    }


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}