package bharat.law.nyayasetu.networking

import bharat.law.nyayasetu.models.AddLspData
import bharat.law.nyayasetu.models.AddLspDataResponse
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.AddUserDataResponse
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.models.AuthUserDataResponse
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.models.RegisterDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("register")
    suspend fun registerUser(
        @Body registerData: RegisterData
    ): Response<RegisterDataResponse>

    @POST("authenticate")
    suspend fun authUser(
        @Body authUserData: AuthUserData
    ): Response<AuthUserDataResponse>

    @POST("/add/lsp")
    suspend fun addLSP(
        @Header("Authorization") authToken: String,
        @Body addLspData: AddLspData
    ): Response<AddLspDataResponse>

    @POST("/add/client")
    suspend fun addClient(
        @Header("Authorization") authToken: String,
        @Body addUserData: AddUserData
    ): Response<AddUserDataResponse>

    @GET("/get/lsp")
    suspend fun getLSP(
        @Header("Authorization") authToken: String
    ): Response<List<GetLawyersResponse>>
}