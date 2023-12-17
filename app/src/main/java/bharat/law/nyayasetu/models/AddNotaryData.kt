package bharat.law.nyayasetu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddNotaryData(
    val aadhar: String? = null,
    val age: Int? = null,
    val availability: String? = null,
    val bar_association_reg_no: String? = null,
    val commission_expiry: String? = null,
    val commission_no: String? = null,
    val cost: Int? = null,
    val document_url: String? = null,
    val experience: Int? = null,
    val gender: String? = null,
    val incentive_level: String? = null,
    val jurisdiction_covered: String? = null,
    val languages_spoken: String? = null,
    val location: String? = null,
    val name: String? = null,
    val points: Int? = null,
    val profile_pic: String? = null,
    val rating: Int? = null,
    val summary: String? = null
): Parcelable