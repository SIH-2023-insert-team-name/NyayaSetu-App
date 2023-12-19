package bharat.law.nyayasetu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClientPersonalDetailsData(
    val name: String? = null,
    val aadhar: String? = null,
    val gender: String? = null,
    val age: String? = null,
    val city: String? = null,
    val preferredLanguages: String? = null,
): Parcelable
