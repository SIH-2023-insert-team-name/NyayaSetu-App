package bharat.law.nyayasetu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.models.ChatMessage

class ChatMessageAdapter(context: Context, private val messages: List<ChatMessage>) :
    ArrayAdapter<ChatMessage>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val message = getItem(position)
        val layout = if (message?.isClient == true) R.layout.item_message_user1 else R.layout.item_message_user2

        val rowView = convertView ?: LayoutInflater.from(context).inflate(layout, parent, false)

        val textView = rowView.findViewById<TextView>(R.id.text_view_message)
        textView.text = message?.text

        return rowView
    }
}
