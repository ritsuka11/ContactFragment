package vn.edu.hust.activityexamples

// AddContactFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddContactFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var addButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        nameEditText = view.findViewById(R.id.editName)
        phoneEditText = view.findViewById(R.id.editPhone)
        emailEditText = view.findViewById(R.id.editEmail)
        addButton = view.findViewById(R.id.addContactButton)

        addButton.setOnClickListener {
            addContact()
        }

        return view
    }

    private fun addContact() {
        val name = nameEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val email = emailEditText.text.toString()

        // TODO: Add logic to handle adding a contact (e.g., display a message, add to the database, etc.)
    }
}
