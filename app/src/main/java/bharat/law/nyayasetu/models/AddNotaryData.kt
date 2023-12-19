package bharat.law.nyayasetu.models

import android.os.Parcelable
import android.view.PixelCopy.Request
import kotlinx.parcelize.Parcelize
import okhttp3.MultipartBody
import okhttp3.RequestBody


data class AddNotaryData(
    val aadhar: RequestBody,
    val age: RequestBody,
    val availability: RequestBody,
    val bar_association_reg_no: RequestBody,
    val commission_expiry: RequestBody,
    val commission_no: RequestBody,
    val cost: RequestBody,
    val document_url: MultipartBody.Part,
    val experience: RequestBody,
    val gender: RequestBody,
    val incentive_level: RequestBody,
    val jurisdiction_covered: RequestBody,
    val languages_spoken: RequestBody,
    val location: RequestBody,
    val name: RequestBody,
    val points: RequestBody,
    val profile_pic: RequestBody,
    val rating: RequestBody,
    val summary: RequestBody
)