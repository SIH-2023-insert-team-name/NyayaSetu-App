package bharat.law.nyayasetu.lawyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bharat.law.nyayasetu.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonalDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_personal_details, container, false)
    }

}