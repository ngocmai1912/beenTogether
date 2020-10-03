package com.nmai.beentogether.ui.home.lovequote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentLoveDateDialogBinding
import com.nmai.beentogether.databinding.FragmentLoveQuoteDialogBinding
import com.nmai.beentogether.repository.Data
import kotlin.random.Random

class LoveQuoteDialogFragment : DialogFragment() {
    lateinit var binding : FragmentLoveQuoteDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoveQuoteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var random = java.util.Random()
        var index = random.nextInt(Data.listQuote.size)
        binding.tvLoveQuote.text = Data.listQuote[index].loveQuote
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}