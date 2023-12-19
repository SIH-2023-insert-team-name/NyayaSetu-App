package bharat.law.nyayasetu.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bharat.law.nyayasetu.models.ChatResponseItemData
import bharat.law.nyayasetu.models.GetChatParameter
import bharat.law.nyayasetu.models.MessageParameter
import bharat.law.nyayasetu.networking.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val getMessageResponse: MutableLiveData<Response<List<ChatResponseItemData>>> = MutableLiveData()

    val postMessageResponse: MutableLiveData<Response<ChatResponseItemData>> = MutableLiveData()




    fun getMessages(getChatParameter: GetChatParameter){
        viewModelScope.launch {
            val getMsgResp = repository.getMessages(getChatParameter)
            getMessageResponse.value = getMsgResp

        }
    }

    fun postMessages(messageParameter: MessageParameter){
        viewModelScope.launch {
            val postMsgResp = repository.postMessages(messageParameter)
            postMessageResponse.value = postMsgResp
        }
    }

}