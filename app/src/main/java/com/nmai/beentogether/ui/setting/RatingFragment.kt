package com.nmai.beentogether.ui.setting

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentRattingBinding

class RatingFragment : DialogFragment() {
    lateinit var binding : FragmentRattingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRattingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rating.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(p0: RatingBar?, rating: Float, p2: Boolean) {
                if(rating < 4){
                    dismiss()
                }
                else{
                    startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=_4CiX0waEr0")))
                }
            }
        })
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}