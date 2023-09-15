package bharat.law.nyayasetu.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bharat.law.nyayasetu.models.AddLspData
import bharat.law.nyayasetu.models.AddLspDataResponse
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.AddUserDataResponse
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.models.AuthUserDataResponse
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.models.RegisterDataResponse
import bharat.law.nyayasetu.networking.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LawyerViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _registerResponse = MutableLiveData<Response<RegisterDataResponse>>()
    val registerResponse: MutableLiveData<Response<RegisterDataResponse>> get() = _registerResponse


    private val _authResponse = MutableLiveData<Response<AuthUserDataResponse>>()
    val authResponse: MutableLiveData<Response<AuthUserDataResponse>> get() = _authResponse


    private val _addLSPResponse = MutableLiveData<Response<AddLspDataResponse>>()
    val addLSPResponse: MutableLiveData<Response<AddLspDataResponse>> get() = _addLSPResponse


    private val _addClientResponse = MutableLiveData<Response<AddUserDataResponse>>()
    val addClientResponse: MutableLiveData<Response<AddUserDataResponse>> get() = _addClientResponse


    private val _getLSPResponse = MutableLiveData<Response<List<GetLawyersResponse>>>()
    val getLSPResponse: MutableLiveData<Response<List<GetLawyersResponse>>> get() = _getLSPResponse


    fun registerUser(registerData: RegisterData) {
        viewModelScope.launch {
            val rResponse = repository.registerUser(registerData)
            _registerResponse.value = rResponse
        }
    }

    fun authUser(authUserData: AuthUserData) {
        viewModelScope.launch {
            val rResponse = repository.authUser(authUserData)
            _authResponse.value = rResponse
        }
    }

    fun addLSP(authToken: String, addLspData: AddLspData) {
        viewModelScope.launch {
            val addLResponse = repository.addLSP(authToken, addLspData)
            _addLSPResponse.value = addLResponse
        }
    }

    fun addClient(authToken: String, addUserData: AddUserData) {
        viewModelScope.launch {
            val addCResponse = repository.addClient(authToken, addUserData)
            _addClientResponse.value = addCResponse
        }
    }

    fun getLSP(authToken: String) {
        viewModelScope.launch {
            val getResponse = repository.getLSP(authToken)
            _getLSPResponse.value = getResponse
        }
    }
}