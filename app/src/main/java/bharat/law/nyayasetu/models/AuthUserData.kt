package bharat.law.nyayasetu.models

import com.google.gson.annotations.SerializedName

data class AuthUserData(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null
)
