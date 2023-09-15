package bharat.law.nyayasetu.models

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("isLSP")
    var isLSP: Int? = null
)
