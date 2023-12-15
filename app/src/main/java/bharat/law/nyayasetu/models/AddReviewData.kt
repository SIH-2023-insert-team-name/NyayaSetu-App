package bharat.law.nyayasetu.models

data class AddReviewData(
    val client_email: String,
    val lawyer_email: String,
    val rating: Double,
    val text: String
)