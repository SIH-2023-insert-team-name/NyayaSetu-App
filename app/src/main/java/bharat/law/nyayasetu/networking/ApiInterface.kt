package bharat.law.nyayasetu.networking

import bharat.law.nyayasetu.models.AddCaseData
import bharat.law.nyayasetu.models.AddCaseResponse
import bharat.law.nyayasetu.models.AddDocWriterData
import bharat.law.nyayasetu.models.AddDocWriterResponseData
import bharat.law.nyayasetu.models.AddLawyerData
import bharat.law.nyayasetu.models.AddLawyerResponseData
import bharat.law.nyayasetu.models.AddLspDataResponse
import bharat.law.nyayasetu.models.AddNotaryData
import bharat.law.nyayasetu.models.AddNotaryResponseData
import bharat.law.nyayasetu.models.AddReviewData
import bharat.law.nyayasetu.models.AddReviewResponse
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.AddUserDataResponse
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.models.AuthUserDataResponse
import bharat.law.nyayasetu.models.ChatResponseItemData
import bharat.law.nyayasetu.models.GetChatParameter
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.MessageParameter
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.models.RegisterDataResponse
import bharat.law.nyayasetu.models.UserReviewsDataResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {
    @POST("register")
    suspend fun registerUser(
        @Body registerData: RegisterData
    ): Response<RegisterDataResponse>

    @POST("authenticate")
    suspend fun authUser(
        @Body authUserData: AuthUserData
    ): Response<AuthUserDataResponse>

    @Multipart
    @POST("/add/lawyer")
    suspend fun addLawyer(
        @Header("Authorization") authToken: String,
        @Part("aadhar") aadhar: RequestBody,
        @Part("age") age: RequestBody,
        @Part("availability") availability: RequestBody,
        @Part("bar_association_reg_no") barAssociationRegNo: RequestBody,
        @Part("category") category: RequestBody,
        @Part("cost") cost: RequestBody,
        @Part documentUrl: MultipartBody.Part,
        @Part("experience") experience: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("incentive_level") incentiveLevel: RequestBody,
        @Part("languages_spoken") languagesSpoken: RequestBody,
        @Part("location") location: RequestBody,
        @Part("name") name: RequestBody,
        @Part("points") points: RequestBody,
        @Part("profile_pic") profilePic: RequestBody,
        @Part("rating") rating: RequestBody,
        @Part("summary") summary: RequestBody
    ): Response<AddLawyerResponseData>

    @POST("/add/docwriter")
    suspend fun addDocWriter(
        @Header("Authorization") authToken: String,
        @Body addDocWriterData: AddDocWriterData
    ): Response<AddDocWriterResponseData>


    @Multipart
    @POST("/add/notary")
    suspend fun addNotary(
        @Header("Authorization") authToken: String,
        @Part("aadhar") aadhar: RequestBody,
        @Part("age") age: RequestBody,
        @Part("availability") availability: RequestBody,
        @Part("bar_association_reg_no") barAssociationRegNo: RequestBody,
        @Part("commission_expiry") commissionExpiry: RequestBody,
        @Part("commission_no") commissionNo: RequestBody,
        @Part("cost") cost: RequestBody,
        @Part documentUrl: MultipartBody.Part,
        @Part("experience") experience: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("incentive_level") incentiveLevel: RequestBody,
        @Part("jurisdiction_covered") jurisdictionCovered: RequestBody,
        @Part("languages_spoken") languagesSpoken: RequestBody,
        @Part("location") location: RequestBody,
        @Part("name") name: RequestBody,
        @Part("points") points: RequestBody,
        @Part("profile_pic") profilePic: RequestBody,
        @Part("rating") rating: RequestBody,
        @Part("summary") summary: RequestBody
    ): Response<AddNotaryResponseData>

    @POST("/add/client")
    suspend fun addClient(
        @Header("Authorization") authToken: String,
        @Body addUserData: AddUserData
    ): Response<AddUserDataResponse>

    @GET("/get/lawyers")
    suspend fun getLawyers(
        @Header("Authorization") authToken: String
    ): Response<List<GetLawyersResponse>>

    @GET("/sorted")
    suspend fun getRecommendedLawyers(): Response<List<GetLawyersResponse>>

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

    @GET("/getChat")
    suspend fun getMessages(
        @Body getChatParameter: GetChatParameter
    ): Response<List<ChatResponseItemData>>

    @POST("/addChat")
    suspend fun postMessages(
        @Body messageParameter: MessageParameter
    ): Response<ChatResponseItemData>

    @POST("/addcase")
    suspend fun addCase(
        @Header("Authorization") authToken: String,
        @Body addCaseData: AddCaseData
    ): Response<AddCaseResponse>
}