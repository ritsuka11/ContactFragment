package vn.edu.hust.activityexamples

// ContactListFragment.kt
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ContactListFragment : Fragment() {

    private val contacts = listOf(
        Contact(1, "Josefina Lehner", "123-456-7890", "john.doe@gmail.com"),
        Contact(2, "Stuart Vandervort II", "987-654-3210", "jane.smith@gmail.com"),
        Contact(3,"Maddison Russel", "452-268-4578","maddison@gmail.com"),
        Contact(4,"Halie Morar II","123-456-7390", "haliecute@gmail.com"),
        Contact(5,"Karelle Simonis", "123-456-7840","karelle@gmail.com"),
        Contact(6,"Hannah Welch", "123-456-4578", "hannah@gmail.com"),
        Contact(7,"Fanny Frami", "324-145-6445","halffanny@gmail.com"),
        Contact(8,"Elfrieda Wisozk","124-988-2929","elfried@gmail.com")
    )

    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        val listView: ListView = view.findViewById(R.id.listView)

        contactAdapter = ContactAdapter(requireContext(), contacts)
        listView.adapter = contactAdapter

        // Registering the ListView for the context menu
        registerForContextMenu(listView)

        // Handling item click for opening the detail fragment
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contactAdapter.getItem(position) as Contact
            showDetailFragment(selectedContact)
        }

        return view
    }

    private fun showDetailFragment(contact: Contact) {
        val fragment = ContactDetailFragment.newInstance(contact)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        val selectedContact = contactAdapter.getItem(position) as Contact

        menu.add(0, 0, 0, "Call")
        menu.add(0, 1, 1, "Send SMS")
        menu.add(0, 2, 2, "Send Email")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        val selectedContact = contactAdapter.getItem(position) as Contact

        when (item.itemId) {
            0 -> showToast("Call: ${selectedContact.phoneNumber}")
            1 -> showToast("Send SMS to: ${selectedContact.phoneNumber}")
            2 -> showToast("Send Email to: ${selectedContact.email}")
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}

