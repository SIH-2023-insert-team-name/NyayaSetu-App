package bharat.law.nyayasetu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddUserData(
    val aadhar: String? = null,
    val age: String? = null,
    val availability: String? = null,
    val budget: Int? = null,
    val city: String? = null,
    val experience: Int? = null,
    val gender: String? = null,
    val legal_needs: String? = null,
    val name: String? = null,
    val preferred_language: String? = null
): Parcelable