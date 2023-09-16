package bharat.law.nyayasetu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.ItemAppointmentBinding
import bharat.law.nyayasetu.models.GetLawyersResponse

class ConsultancyAdapter(
    val context: Context,
    var lawyerList:MutableList<GetLawyersResponse>
):
RecyclerView.Adapter<ConsultancyAdapter.ConsultancyViewHolder>()
{
    var onItemClick : ((GetLawyersResponse) -> Unit)? = null
    class ConsultancyViewHolder(val binding:ItemAppointmentBinding,context: Context):
        RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ConsultancyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppointmentBinding.inflate(layoutInflater, parent, false)
        return ConsultancyViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: ConsultancyViewHolder, position: Int) {
       val lawyer = lawyerList[position]

        holder.binding.tvLawyerName.text = lawyer.name
        holder.binding.tvLawyerType.text = lawyer.category
        holder.binding.tvExperince.text = lawyer.experience.toString()+" years"

        holder.itemView.setOnClickListener{
        onItemClick?.invoke(lawyer)
        }
    }

    override fun getItemCount():Int{
        return lawyerList.size
    }
}