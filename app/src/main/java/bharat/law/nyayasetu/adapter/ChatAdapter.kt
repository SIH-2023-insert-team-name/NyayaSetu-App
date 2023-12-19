package bharat.law.nyayasetu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.models.ChatResponseItemData
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants

class ChatAdapter(val context: Context, val msgList: ArrayList<ChatResponseItemData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_CLIENT = 1
    val ITEM_LSP = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1) {
            //inflating received
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.item_client_message, parent, false)
            return CustomerViewHolder(view)
        } else {
            //inflating send
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.item_lsp_message, parent, false)
            return AgentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    fun updateMessages(newMessages: ChatResponseItemData) {
        msgList.add(0, newMessages)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = msgList[position]
        if (holder.javaClass == AgentViewHolder::class.java) {
            val viewHolder = holder as AgentViewHolder
            currentMessage.message.also { viewHolder.tvSent.text = it }

        } else {
            val viewHolder = holder as CustomerViewHolder
            currentMessage.message.also { viewHolder.tvReceived.text = it }

        }
    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = msgList[position]

        if (currentMessage.sender_email == AppSession(context).getString(Constants.CLIENT_EMAIL)) {
            return ITEM_CLIENT
        } else {
            return ITEM_LSP
        }
    }

    class AgentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSent = itemView.findViewById<TextView>(R.id.tvAgent)
    }

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvReceived = itemView.findViewById<TextView>(R.id.tvLsp)
    }
}