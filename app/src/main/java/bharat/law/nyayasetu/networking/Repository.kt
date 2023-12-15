package bharat.law.nyayasetu.networking

import bharat.law.nyayasetu.models.AddDocWriterData
import bharat.law.nyayasetu.models.AddLawyerData
import bharat.law.nyayasetu.models.AddLspDataResponse
import bharat.law.nyayasetu.models.AddNotaryData
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.AddUserDataResponse
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.models.AuthUserDataResponse
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.models.RegisterDataResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun registerUser(registerData: RegisterData): Response<RegisterDataResponse> {
        return apiInterface.registerUser(registerData)
    }

    suspend fun authUser(authUserData: AuthUserData): Response<AuthUserDataResponse> {
        return apiInterface.authUser(authUserData)
    }

    suspend fun addLawyer(authToken: String,addLspData: AddLawyerData): Response<AddLspDataResponse> {
        return apiInterface.addLawyer(authToken,addLspData)
    }

    suspend fun addNotary(authToken: String,addNotaryData: AddNotaryData): Response<AddLspDataResponse> {
        return apiInterface.addNotary(authToken,addNotaryData)
    }

    suspend fun addDocWriter(authToken: String,addDocWriterData: AddDocWriterData): Response<AddLspDataResponse> {
        return apiInterface.addDocWriter(authToken,addDocWriterData)
    }

    suspend fun addClient(authToken: String,addUserData: AddUserData): Response<AddUserDataResponse> {
        return apiInterface.addClient(authToken,addUserData)
    }

    suspend fun getLSP(authToken: String): Response<List<GetLawyersResponse>> {
        return apiInterface.getLawyers(authToken)
    }
}