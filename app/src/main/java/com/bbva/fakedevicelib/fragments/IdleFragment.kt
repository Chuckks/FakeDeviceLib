package com.bbva.fakedevicelib.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbva.fakedevicelib.R
import com.bbva.fakedevicelib.databinding.FragmentIdleBinding

class IdleFragment : Fragment(R.layout.fragment_idle) {
    private lateinit var binding: FragmentIdleBinding
    var listener: View.OnClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIdleBinding.bind(view)
        binding.btnStartTransaction.setOnClickListener(listener)

    }

}