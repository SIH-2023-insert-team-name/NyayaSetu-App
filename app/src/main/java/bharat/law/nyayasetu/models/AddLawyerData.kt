package bharat.law.nyayasetu.models

data class AddLawyerData(
    val aadhar: String,
    val age: Int,
    val availability: String,
    val bar_association_reg_no: String,
    val category: String,
    val cost: Int,
    val document_url: String,
    val email: String,
    val experience: Int,
    val gender: String,
    val incentive_level: String,
    val languages_spoken: String,
    val location: String,
    val name: String,
    val points: Int,
    val profile_pic: String,
    val rating: Double,
    val serial_no: Int,
    val summary: String,
    val username: String
)