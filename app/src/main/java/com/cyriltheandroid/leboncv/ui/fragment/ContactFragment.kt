package com.cyriltheandroid.leboncv.ui.fragment

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.adapter.ContactAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentContactBinding
import com.cyriltheandroid.leboncv.model.ItemContact
import com.cyriltheandroid.leboncv.ui.viewmodel.ProfileViewModel
import com.cyriltheandroid.leboncv.utils.CALL_INDEX
import com.cyriltheandroid.leboncv.utils.EMAIL_INDEX
import com.cyriltheandroid.leboncv.utils.setCloseFragmentOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "ContactFragment"

@AndroidEntryPoint
class ContactFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentContactBinding

    @Inject
    lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        setCloseFragmentOnClick(binding.backArrowImageButton)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        profileViewModel.itemsContact.observe(viewLifecycleOwner) {
            contactAdapter.contactList = it
            contactAdapter.clickListener.onItemClick = { idx, obj ->
                val contact = obj as ItemContact
                manageItemContactClick(idx, contact)
            }
            contactAdapter.clickListener.onCopyButtonClick = { _, obj ->
                val textToCopy = obj as String
                copyTextOnClipboard(textToCopy)
                Toast.makeText(
                    context,
                    getString(R.string.copy_to_clipboard_msg, textToCopy),
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.contactRecyclerView.adapter = contactAdapter
        }
    }

    private fun manageItemContactClick(index: Int, contact: ItemContact) {
        when (index) {
            EMAIL_INDEX -> {
                val email = contact.value
                sendEmailOnClick(email)
            }
            CALL_INDEX -> {
                val phoneNumber = contact.value
                callPhoneNumberOnClick(phoneNumber)
            }
        }
    }

    private fun copyTextOnClipboard(text: String) {
        val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText(getString(R.string.contact_title), text)
        clipboard.setPrimaryClip(clip)
    }

    private fun sendEmailOnClick(email: String) {
        try {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.contact_email_subject))
            }
            val title = getString(R.string.email_chooser_title, email)
            val chooser = Intent.createChooser(emailIntent, title)
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, e.toString())
        }
    }

    private fun callPhoneNumberOnClick(phoneNumber: String) {
        val number = Uri.parse("tel:$phoneNumber")
        val callIntent = Intent(Intent.ACTION_DIAL, number)
        startActivity(callIntent)
    }
}