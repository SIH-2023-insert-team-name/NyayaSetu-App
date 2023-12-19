package bharat.law.nyayasetu.models

import com.google.gson.annotations.SerializedName

data class ChatResponseData(
    @SerializedName("_id")
    val _id: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("sender_email")
    val sender_email: String? = null,
    @SerializedName("receiver_email")
    val receiver_email: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
)
