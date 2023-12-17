package bharat.law.nyayasetu.models

data class LawyerDetailsResponse(
    val __v: Int,
    val _id: String,
    val aadhar: String,
    val age: Int,
    val availability: String,
    val bar_association_reg_no: String,
    val category: String,
    val cost: Int,
    val createdAt: String,
    val document_url: String,
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
    val updatedAt: String
)