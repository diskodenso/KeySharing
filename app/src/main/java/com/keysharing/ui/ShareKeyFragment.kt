package com.keysharing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keysharing.R

class ShareKeyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // lädt das Layout share_key.xml
        return inflater.inflate(R.layout.share_key, container, false)
    }
}
