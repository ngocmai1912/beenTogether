package com.nmai.beentogether.ui.home.user.nameuser

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentNameUserDialogBinding
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.ui.home.HomeViewModel
import com.nmai.beentogether.ui.home.user.avataruser.ChangeTextDialogFragment
import java.util.*

class NameUserDialogFragment : DialogFragment() {
    lateinit var binding : FragmentNameUserDialogBinding
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNameUserDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.changeName.setOnClickListener {
            ChangeTextDialogFragment().show(childFragmentManager, "change name")
        }
        binding.changeGender.setOnClickListener {
            GenderFragment().show(childFragmentManager, "change gender")
        }

        /**
         * set birthday
         */
        viewModel.change.observe(viewLifecycleOwner){index ->
            when(index){
                1 -> {
                    viewModel.userInfo1.observe(viewLifecycleOwner){user->
                        binding.chooseBirth.setOnClickListener {
                            val calendar = Calendar.getInstance()
                            var day = calendar.get(Calendar.DAY_OF_MONTH)
                            var month = calendar.get(Calendar.MONTH)
                            var year = calendar.get(Calendar.YEAR)
                            val datePickerDialog = DatePickerDialog(requireContext(),
                                R.style.DialogTheme, DatePickerDialog.OnDateSetListener {
                                    _, yearr, monthOfYear, dayOfMonth -> viewModel.setUserInfo1(
                                User(
                                    user._id,
                                    user.nickName,
                                    user.avatar,
                                    "${dayOfMonth}/${monthOfYear+1}/${yearr}",
                                    user.gender,
                                    user.borderColorCode
                                )
                            )
                                day = dayOfMonth
                                month = monthOfYear
                            }, year, month, day).show()
                        }
                    }
                }
                2 -> {
                    viewModel.userInfo2.observe(viewLifecycleOwner){user->
                        binding.chooseBirth.setOnClickListener {
                            val calendar = Calendar.getInstance()
                            var day = calendar.get(Calendar.DAY_OF_MONTH)
                            var month = calendar.get(Calendar.MONTH)
                            var year = calendar.get(Calendar.YEAR)
                            val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener {
                                    _, yearr, monthOfYear, dayOfMonth -> viewModel.setUserInfo2(
                                User(
                                    user._id,
                                    user.nickName,
                                    user.avatar,
                                    "${dayOfMonth}/${monthOfYear+1}/${yearr}",
                                    user.gender,
                                    user.borderColorCode
                                )
                            )
                                day = dayOfMonth
                                month = monthOfYear
                            }, year, month, day).show()
                        }
                    }
                }
            }
        }
    }
}