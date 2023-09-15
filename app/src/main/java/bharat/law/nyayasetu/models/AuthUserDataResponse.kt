package bharat.law.nyayasetu.models

data class AuthUserDataResponse(
    val isLSP: Int,
    val message: String,
    val token: String
)