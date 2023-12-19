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
import bharat.law.nyayasetu.models.AddUserData
import bharat.law.nyayasetu.models.AddUserDataResponse
import bharat.law.nyayasetu.models.AuthUserData
import bharat.law.nyayasetu.models.AuthUserDataResponse
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.RegisterData
import bharat.law.nyayasetu.models.RegisterDataResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun registerUser(registerData: RegisterData): Response<RegisterDataResponse> {
        return apiInterface.registerUser(registerData)
    }

    suspend fun authUser(authUserData: AuthUserData): Response<AuthUserDataResponse> {
        return apiInterface.authUser(authUserData)
    }

    suspend fun addCase(authToken: String, addCaseData: AddCaseData): Response<AddCaseResponse>{
        return apiInterface.addCase(authToken, addCaseData)
    }

    suspend fun addLawyer(
        authToken: String,
        aadhar: RequestBody,
        age: RequestBody,
        availability: RequestBody,
        barAssociationRegNo: RequestBody,
        category: RequestBody,
        cost: RequestBody,
        documentUrl: MultipartBody.Part,
        experience: RequestBody,
        gender: RequestBody,
        incentiveLevel: RequestBody,
        languagesSpoken: RequestBody,
        location: RequestBody,
        name: RequestBody,
        points: RequestBody,
        profilePic: RequestBody,
        rating: RequestBody,
        summary: RequestBody
    ): Response<AddLawyerResponseData> {
        return apiInterface.addLawyer(
            authToken,
            aadhar,
            age,
            availability,
            barAssociationRegNo,
            category,
            cost,
            documentUrl,
            experience,
            gender,
            incentiveLevel,
            languagesSpoken,
            location,
            name,
            points,
            profilePic,
            rating,
            summary
        )
    }

    suspend fun addNotary(
        authToken: String,
        aadhar: RequestBody,
        age: RequestBody,
        availability: RequestBody,
        barAssociationRegNo: RequestBody,
        commissionExpiry: RequestBody,
        commissionNo: RequestBody,
        cost: RequestBody,
        documentUrl: MultipartBody.Part,
        experience: RequestBody,
        gender: RequestBody,
        incentiveLevel: RequestBody,
        jurisdictionCovered: RequestBody,
        languagesSpoken: RequestBody,
        location: RequestBody,
        name: RequestBody,
        points: RequestBody,
        profilePic: RequestBody,
        rating: RequestBody,
        summary: RequestBody
    ): Response<AddNotaryResponseData> {
        return apiInterface.addNotary(
            authToken,
            aadhar,
            age,
            availability,
            barAssociationRegNo,
            commissionExpiry,
            commissionNo,
            cost,
            documentUrl,
            experience,
            gender,
            incentiveLevel,
            jurisdictionCovered,
            languagesSpoken,
            location,
            name,
            points,
            profilePic,
            rating,
            summary
        )
    }

    suspend fun addDocWriter(authToken: String,addDocWriterData: AddDocWriterData): Response<AddDocWriterResponseData> {
        return apiInterface.addDocWriter(authToken,addDocWriterData)
    }

    suspend fun addClient(authToken: String,addUserData: AddUserData): Response<AddUserDataResponse> {
        return apiInterface.addClient(authToken,addUserData)
    }

    suspend fun getLSP(authToken: String): Response<List<GetLawyersResponse>> {
        return apiInterface.getLawyers(authToken)
    }
}