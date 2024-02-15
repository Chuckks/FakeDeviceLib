package com.bbva.fakedevicelib.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbva.fakedevicelib.R
import com.bbva.fakedevicelib.databinding.FragmentCardPresentBinding

class CardPresentFragment : Fragment(R.layout.fragment_card_present) {
    private lateinit var binding: FragmentCardPresentBinding
    var listener: View.OnClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardPresentBinding.bind(view)
        binding.btnCancelar.setOnClickListener(listener)
    }
}