package bharat.law.nyayasetu.models

import com.google.gson.annotations.SerializedName

data class GetChatParameter(
    @SerializedName("receiver_email")
    val receiver_email: String?=null,
    @SerializedName("sender_email")
    val sender_email: String?=null
)