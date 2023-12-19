package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentFirebaseChatBinding
import bharat.law.nyayasetu.databinding.FragmentRegisterCaseBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseChatFragment : Fragment() {
    private var _binding: FragmentFirebaseChatBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var database: FirebaseDatabase
    private lateinit var messagesReference: DatabaseReference
    private lateinit var messageAdapter: ArrayAdapter<String>
    private lateinit var messagesList: MutableList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirebaseChatBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseApp.initializeApp(requireContext())
        database = FirebaseDatabase.getInstance()
        messagesReference = database.getReference("messages")

        messagesList = ArrayList()
        messageAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, messagesList)
        binding.listViewChat.adapter = messageAdapter

        binding.buttonSend.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            sendMessage(message)
            binding.editTextMessage.text.clear()
        }

        messagesReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val message = dataSnapshot.getValue(String::class.java)
                message?.let {
                    messagesList.add(it)
                    messageAdapter.notifyDataSetChanged()
                    binding.listViewChat.setSelection(messageAdapter.count - 1)
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    private fun sendMessage(message: String) {
        val key = messagesReference.push().key
        key?.let {
            messagesReference.child(it).setValue(message)
        }
    }

}