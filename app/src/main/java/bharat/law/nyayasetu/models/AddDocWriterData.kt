package bharat.law.nyayasetu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddDocWriterData(
    val aadhar: String? = null,
    val age: Int? = null,
    val availability: String? = null,
    val cost: Double? = null,
    val document_url: String? = null,
    val experience: Int? = null,
    val gender: String? = null,
    val incentive_level: String? = null,
    val languages_spoken: String? = null,
    val location: String? = null,
    val name: String? = null,
    val points: Int? = null,
    val profile_pic: String? = null,
    val rating: Double? = null,
    val summary: String? = null,
    val username: String? = null
): Parcelable