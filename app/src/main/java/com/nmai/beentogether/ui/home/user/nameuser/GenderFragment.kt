package com.nmai.beentogether.ui.home.user.nameuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nmai.beentogether.databinding.FragmentGenderBinding
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.ui.home.HomeViewModel

class GenderFragment : DialogFragment() {
    lateinit var binding : FragmentGenderBinding
    private val viewModel : HomeViewModel by activityViewModels()
    private var index : Int = 0
    private var user : User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.change.observe(viewLifecycleOwner) { x ->
            index = x
            when (index) {
                1 -> {
                    viewModel.userInfo1.observe(viewLifecycleOwner){user->
                        binding.btnFemale.setOnClickListener {
                            user.gender = "female"
                            dismiss()
                        }
                        binding.btnMale.setOnClickListener {
                            user.gender = "male"
                            dismiss()
                        }
                        binding.btnHide.setOnClickListener {
                            user.gender = "hide"
                            dismiss()
                        }
                        viewModel.setUserInfo1(user)
                    }
                }
                2 -> {
                    viewModel.userInfo2.observe(viewLifecycleOwner) { user ->
                        binding.btnFemale.setOnClickListener {
                            user.gender = "female"
                            dismiss()
                        }
                        binding.btnMale.setOnClickListener {
                            user.gender = "male"
                            dismiss()
                        }
                        binding.btnHide.setOnClickListener {
                            user.gender = "hide"
                            dismiss()
                        }
                        viewModel.setUserInfo2(user)
                    }
                }
            }
        }
    }
}