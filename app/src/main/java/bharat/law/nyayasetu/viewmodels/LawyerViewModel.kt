package bharat.law.nyayasetu.viewmodels

import androidx.lifecycle.ViewModel
import bharat.law.nyayasetu.networking.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LawyerViewModel @Inject constructor(private val repository: Repository): ViewModel() {
}