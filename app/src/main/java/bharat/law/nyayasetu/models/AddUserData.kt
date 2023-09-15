package bharat.law.nyayasetu.models

data class AddUserData(
    val aadhar: String,
    val age: String,
    val availability: String,
    val budget: Int,
    val city: String,
    val experience: Int,
    val gender: String,
    val legal_needs: String,
    val name: String,
    val preferred_language: String
)