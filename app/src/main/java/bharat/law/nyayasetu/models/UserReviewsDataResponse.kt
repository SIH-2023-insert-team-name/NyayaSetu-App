package bharat.law.nyayasetu.models

data class UserReviewsDataResponse(
    val __v: Int,
    val _id: String,
    val client_email: String,
    val createdAt: String,
    val lawyer_email: String,
    val rating: Double,
    val text: String,
    val updatedAt: String
)