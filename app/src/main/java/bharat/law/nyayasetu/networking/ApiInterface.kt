package bharat.law.nyayasetu.networking

import bharat.law.nyayasetu.models.AddDocWriterData
import bharat.law.nyayasetu.models.AddLawyerData
import bharat.law.nyayasetu.models.AddLspDataResponse
import bharat.law.nyayasetu.models.AddNotaryData
import bharat.law.nyayasetu.models.AddReviewData
import bharat.law.nyayasetu.models.AddReviewResponse
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.AddUserDataResponse
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.models.AuthUserDataResponse
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.models.RegisterDataResponse
import bharat.law.nyayasetu.models.UserReviewsDataResponse
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

    @POST("/add/lawyer")
    suspend fun addLawyer(
        @Header("Authorization") authToken: String,
        @Body addLawyerData: AddLawyerData
    ): Response<AddLspDataResponse>

    @POST("/add/notary")
    suspend fun addDocWriter(
        @Header("Authorization") authToken: String,
        @Body addDocWriterData: AddDocWriterData
    ): Response<AddLspDataResponse>

    @POST("/add/docwriter")
    suspend fun addNotary(
        @Header("Authorization") authToken: String,
        @Body addNotaryData: AddNotaryData
    ): Response<AddLspDataResponse>

    @POST("/add/client")
    suspend fun addClient(
        @Header("Authorization") authToken: String,
        @Body addUserData: AddUserData
    ): Response<AddUserDataResponse>

    @GET("/get/lawyers")
    suspend fun getLawyers(
        @Header("Authorization") authToken: String
    ): Response<List<GetLawyersResponse>>

    @GET("/get/notaries")
    suspend fun getNotaries(
        @Header("Authorization") authToken: String
    ): Response<List<GetLawyersResponse>>

    @GET("/get/lawyers")
    suspend fun getDocWriters(
        @Header("Authorization") authToken: String
    ): Response<List<GetLawyersResponse>>

    @GET("/get/reviews")
    suspend fun getUserReviews(
        @Header("Authorization") authToken: String
    ): Response<List<UserReviewsDataResponse>>

    @POST("/add/review")
    suspend fun addReview(
        @Header("Authorization") authToken: String,
        @Body addReviewData: AddReviewData
    ): Response<AddReviewResponse>

    @GET("/ranks")
    suspend fun getRanks()

    @GET("/leaderboard")
    suspend fun getLeaderboard()
}