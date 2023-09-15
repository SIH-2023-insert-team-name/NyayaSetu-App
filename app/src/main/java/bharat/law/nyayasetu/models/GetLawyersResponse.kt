package bharat.law.nyayasetu.models

data class GetLawyersResponse(
    val __v: Int,
    val _id: String,
    val aadhar: String,
    val age: Int,
    val category: String,
    val cost: Int,
    val createdAt: String,
    val email: String,
    val enrollment_no: String,
    val experience: Int,
    val gender: String,
    val points: Int,
    val profile_pic: String,
    val rating: Double,
    val serial_no: Int,
    val summary: String,
    val updatedAt: String
)