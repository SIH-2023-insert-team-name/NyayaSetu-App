package bharat.law.nyayasetu.client.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import bharat.law.nyayasetu.R
import bharat.law.nyayasetu.databinding.FragmentPaymentBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject


class PaymentFragment : Fragment(), PaymentResultListener {
    private var _binding: FragmentPaymentBinding? = null
    private val binding
        get() = _binding!!
    private var selectedOption = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        binding.buyNowButton.setOnClickListener {
            payment()
            Toast.makeText(requireContext(), "$selectedOption", Toast.LENGTH_SHORT).show()
        }

        binding.cvPlanA.setOnClickListener {
            selectedOption = "Plan A"
            binding.llPlanA.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorAccent
                )
            )
            binding.llPlanB.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tvPlanA1.setTextColor(Color.WHITE)
            binding.tvPlanA2.setTextColor(Color.WHITE)
            binding.tvPlanB1.setTextColor(Color.BLACK)
            binding.tvPlanB2.setTextColor(Color.BLACK)

        }

        binding.cvPlanB.setOnClickListener {
            selectedOption = "Plan B"
            binding.llPlanA.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.llPlanB.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorAccent
                )
            )
            binding.tvPlanA1.setTextColor(Color.BLACK)
            binding.tvPlanA2.setTextColor(Color.BLACK)
            binding.tvPlanB1.setTextColor(Color.WHITE)
            binding.tvPlanB2.setTextColor(Color.WHITE)

        }

        return binding.root
    }


    private fun payment() {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_CsJK0mxcTnqyKp") // Replace with your actual Razorpay key ID

        try {
            val options = JSONObject()
            options.put("name", "Nyayasetu")
            options.put("description", "Subscription payment")
            options.put(
                "image",
                "https://example.com/your_image.png"
            ) // Replace with a direct image URL
            options.put("currency", "INR")
            val amount =
                if (selectedOption == "Plan A") 7500 else 18000
            options.put(
                "amount",
                amount
            ) // Payment amount in paise (100 INR = 10000 paise) // Payment amount in paise (100 INR)
            options.put("theme.color", "#009688")

            // Open Razorpay checkout activity
            checkout.open(requireActivity(), options)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(requireContext(), "Payment Success", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(requireContext(), "Payment Error! Please try again", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}