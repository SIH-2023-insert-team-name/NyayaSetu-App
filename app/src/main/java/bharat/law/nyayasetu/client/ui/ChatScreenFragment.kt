package bharat.law.nyayasetu.client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.adapter.ChatAdapter
import bharat.law.nyayasetu.databinding.FragmentChatBinding
import bharat.law.nyayasetu.databinding.FragmentChatScreenBinding
import bharat.law.nyayasetu.models.ChatResponseItemData
import bharat.law.nyayasetu.models.GetChatParameter
import bharat.law.nyayasetu.models.GetLawyersResponse
import bharat.law.nyayasetu.models.MessageParameter
import bharat.law.nyayasetu.utils.AppSession
import bharat.law.nyayasetu.utils.Constants
import bharat.law.nyayasetu.viewmodels.ChatViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale

class ChatScreenFragment(
    val data:GetLawyersResponse
) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ChatBottomSheetFragment"
    }

    private val messageViewModel:ChatViewModel by viewModels()
    lateinit var binding:FragmentChatScreenBinding
    private lateinit var chatAdapter: ChatAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val chatInflater = LayoutInflater.from(requireContext())
        binding = FragmentChatScreenBinding.inflate(chatInflater)
        setupFullHeight()
        onClick()
        setupRecyclerView()
        observeResponse()

        return binding.root
    }
    private fun observeResponse() {
        messageViewModel.postMessageResponse.observe(viewLifecycleOwner, Observer {
            val messageSent = it.body() as ChatResponseItemData

            val sentMsg = ChatResponseItemData(
                _id  = messageSent._id,
                receiver_email = messageSent.receiver_email,
                message = messageSent.message,
                sender_email = messageSent.sender_email
            )

            chatAdapter.updateMessages(sentMsg)
            chatAdapter.notifyDataSetChanged()
            binding.sendMessageEditText.text?.clear()
        })

    }

    private fun onClick() {

        binding.btnSend.setOnClickListener {

            val postMessageData = MessageParameter(
                receiver_email = data.email,
                sender_email = context?.let { it1 -> AppSession(it1).getString(Constants.CLIENT_EMAIL) },
                message = binding.sendMessageEditText.text.toString()
            )

            val getMessageData = GetChatParameter(
                receiver_email = data.email,
                sender_email = context?.let { it1 -> AppSession(it1).getString(Constants.CLIENT_EMAIL) },
            )
            messageViewModel.postMessages(postMessageData)
            messageViewModel.getMessages(getMessageData)

        }

        binding.swipeRefresh.setOnRefreshListener {
            observeResponse()
            setupRecyclerView()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {

//        chatAdapter = ChatAdapter(requireContext(),msgList)
        binding.rvChats.setHasFixedSize(true)
        binding.rvChats.adapter = chatAdapter
        chatAdapter.notifyDataSetChanged()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        binding.rvChats.layoutManager = linearLayoutManager
    }

    private fun setupFullHeight() {
        val bottomSheetDialog = dialog as BottomSheetDialog
        val behavior = bottomSheetDialog.behavior
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.isDraggable = false
    }
}