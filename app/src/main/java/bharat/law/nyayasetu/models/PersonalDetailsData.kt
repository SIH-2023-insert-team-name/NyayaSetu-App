package bharat.law.nyayasetu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalDetailsData(
    val name: String? = null,
    val aadhar: String? = null,
    val gender: String? = null,
    val age: Int? = null,
): Parcelable
