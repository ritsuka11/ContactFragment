package vn.edu.hust.activityexamples

// ContactDetailFragment.kt
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContactDetailFragment : Fragment() {

    companion object {
        const val ARG_CONTACT = "arg_contact"

        fun newInstance(contact: Contact): ContactDetailFragment {
            val fragment = ContactDetailFragment()
            val args = Bundle()
            args.putSerializable(ARG_CONTACT, contact)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var avatarImageView: ImageView
    private lateinit var detailNameTextView: TextView
    private lateinit var detailPhoneTextView: TextView
    private lateinit var detailEmailTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_detail, container, false)

        avatarImageView = view.findViewById(R.id.avatarImageView)
        detailNameTextView = view.findViewById(R.id.detailNameTextView)
        detailPhoneTextView = view.findViewById(R.id.detailPhoneTextView)
        detailEmailTextView = view.findViewById(R.id.detailEmailTextView)

        val contact = arguments?.getSerializable(ARG_CONTACT) as? Contact

        contact?.let {
            // Set avatar image (replace with your logic)
            avatarImageView.setImageBitmap(createAvatarBitmap(contact.name))

            // Set full name
            detailNameTextView.text = it.name

            val phoneNumber = "Phone number: " + it.phoneNumber
            // Set phone number
            detailPhoneTextView.text = phoneNumber

            val email = "Email: " + it.email
            // Set email
            detailEmailTextView.text = email
        } ?: run {
            // Handle the case where the contact is null (optional)
        }

        return view
    }

    private fun createAvatarBitmap(name: String): Bitmap {
        val size = 48
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        // Draw a circle background
        paint.color = Color.GRAY
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

        // Draw the first letter of the name
        paint.color = Color.WHITE
        paint.textSize = 24f
        paint.textAlign = Paint.Align.CENTER
        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (paint.descent() + paint.ascent()) / 2)

        // Use uppercase() instead of deprecated toUpperCase()
        canvas.drawText(name.substring(0, 1).uppercase(), xPos.toFloat(), yPos, paint)

        return bitmap
    }
}
