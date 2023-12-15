package bharat.law.nyayasetu.models

data class AddDocWriterData(
    val aadhar: String,
    val age: Int,
    val availability: String,
    val cost: Double,
    val document_url: String,
    val email: String,
    val experience: Int,
    val gender: String,
    val incentive_level: String,
    val languages_spoken: List<String>,
    val location: String,
    val name: String,
    val points: Int,
    val profile_pic: String,
    val rating: Double,
    val summary: String,
    val username: String
)