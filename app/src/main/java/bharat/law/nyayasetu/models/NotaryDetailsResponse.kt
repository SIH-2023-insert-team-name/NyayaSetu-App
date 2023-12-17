package bharat.law.nyayasetu.models

data class NotaryDetailsResponse(
    val __v: Int,
    val _id: String,
    val aadhar: String,
    val age: Int,
    val availability: String,
    val bar_association_reg_no: String,
    val commission_expiry: String,
    val commission_no: String,
    val cost: Int,
    val createdAt: String,
    val experience: Int,
    val gender: String,
    val incentive_level: String,
    val jurisdiction_covered: String,
    val languages_spoken: List<String>,
    val location: String,
    val name: String,
    val points: Int,
    val profile_pic: String,
    val rating: Int,
    val summary: String,
    val updatedAt: String
)