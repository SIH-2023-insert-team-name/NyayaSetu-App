package bharat.law.nyayasetu.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bharat.law.nyayasetu.models.AddDocWriterData
import bharat.law.nyayasetu.models.AddDocWriterResponseData
import bharat.law.nyayasetu.models.AddLawyerData
import bharat.law.nyayasetu.models.AddLawyerResponseData
import bharat.law.nyayasetu.models.AddLspDataResponse
import bharat.law.nyayasetu.models.AddNotaryData
import bharat.law.nyayasetu.models.AddNotaryResponseData
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


    private val _addLawyerResponse = MutableLiveData<Response<AddLawyerResponseData>>()
    val addLawyerResponse: MutableLiveData<Response<AddLawyerResponseData>> get() = _addLawyerResponse

    private val _addDocWriterResponse = MutableLiveData<Response<AddDocWriterResponseData>>()
    val addDocWriterResponse: MutableLiveData<Response<AddDocWriterResponseData>> get() = _addDocWriterResponse

    private val _addNotaryResponse = MutableLiveData<Response<AddNotaryResponseData>>()
    val addNotaryResponse: MutableLiveData<Response<AddNotaryResponseData>> get() = _addNotaryResponse



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

    fun addLawyer(authToken: String, addLspData: AddLawyerData) {
        viewModelScope.launch {
            val addLResponse = repository.addLawyer(authToken, addLspData)
            _addLawyerResponse.value = addLResponse
        }
    }

    fun addNotary(authToken: String, addNotaryData: AddNotaryData) {
        viewModelScope.launch {
            val addNotaryResponse = repository.addNotary(authToken, addNotaryData)
            _addNotaryResponse.value = addNotaryResponse
        }
    }

    fun addDocWriter(authToken: String, addDocWriterData: AddDocWriterData) {
        viewModelScope.launch {
            val addDocWriterResponse = repository.addDocWriter(authToken, addDocWriterData)
            _addDocWriterResponse.value = addDocWriterResponse
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