// File: app/src/main/java/com/keysharing/ui/sharekey/ShareKeyFragment.kt
package com.keysharing.ui.sharekey

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.keysharing.KeySharingApplication
import com.keysharing.R
import com.keysharing.domain.TransferMethod

class ShareKeyFragment : Fragment(R.layout.fragment_share_key) {

    private val viewModel: ShareKeyViewModel by viewModels {
        ShareKeyViewModelFactory(
            requireActivity().application as KeySharingApplication,
            (requireActivity().application as KeySharingApplication).keyRepository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvOwnKey: TextView = view.findViewById(R.id.tvOwnKeyHeader)
        val spinner: Spinner  = view.findViewById(R.id.spinnerTransferMethod)
        val btnShare: Button  = view.findViewById(R.id.btnShareKey)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.transfer_methods,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        viewModel.ownKey.observe(viewLifecycleOwner) { pk ->
            tvOwnKey.text = pk?.key ?: getString(R.string.own_key_header)
        }

        viewModel.status.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        btnShare.setOnClickListener {
            val methodName = spinner.selectedItem as String
            val method = TransferMethod.valueOf(methodName.uppercase().replace(' ', '_'))
            viewModel.shareKey(method)
        }
    }
}
