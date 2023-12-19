package bharat.law.nyayasetu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkDetailsData(
    val bar_reg_np : String? = null,
    val yoe : Int? = null,
    val availability : String? = null,
    val category: String? = null,
    val document_url : String? = null,
    val notary_comm_no : String? = null,
    val comm_expiry_date : String? = null,
    val jurisdiction_area : String? = null,
): Parcelable
