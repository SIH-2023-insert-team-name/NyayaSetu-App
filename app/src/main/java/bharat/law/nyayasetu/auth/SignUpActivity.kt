package bharat.law.nyayasetu.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
    var userType = 0
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
            binding.progressBar.isVisible = true
            email = binding.etEmail.text.toString()
            val name = binding.etName.text.toString()
            val pass = binding.etPassword.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            val result = when (selectedRadioButtonId) {
                R.id.radioButtonLSP -> 1
                R.id.radioButtonClient -> 0
                R.id.radioButtonNPO -> 2
                else -> -1 // No radio button selected
            }

            if (result!=-1){
               if (result==1){
                   userType=1
               } else if(result == 0) {
                   userType = 0
               }
                else{
                    userType = 2
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
                if (userType ==1){
                    val registerData = RegisterData(
                        name,
                        email,
                        pass,
                        userType
                    )
                    lawyerViewModel.registerUser(registerData)

                } else if (userType == 0) {
                    val registerData = RegisterData(
                        name,
                        email,
                        pass,
                        userType
                    )
                    lawyerViewModel.registerUser(registerData)

                }
                else{
                    val registerData = RegisterData(
                        name,
                        email,
                        pass,
                        userType
                    )
                    lawyerViewModel.registerUser(registerData)
                }
                AppSession(this).putString(Constants.NAME, name)

            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

        lawyerViewModel.registerResponse.observe(this, Observer {
            binding.progressBar.isVisible = false
                when(it.code()){
                    Constants.CODE_200->{
                        val data = it.body()
                        if (data?.message == Constants.REGISTER_SUCCESS) {

                            goToLogin()
                        } else {
                            Toast.makeText(this, Constants.OOPS_SW, Toast.LENGTH_SHORT).show()
                        }
                    }
                    Constants.ERR_201->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_400->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_401->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_402->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_403->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_404->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_500->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
                    Constants.ERR_503->{
                        Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                    }
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