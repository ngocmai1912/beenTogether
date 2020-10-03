package com.nmai.beentogether.ui.home.user.avataruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nmai.beentogether.databinding.FragmentChangeTextDialogBinding
import com.nmai.beentogether.ui.home.HomeViewModel

class ChangeTextDialogFragment : DialogFragment() {
    lateinit var binding : FragmentChangeTextDialogBinding
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeTextDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * change text : index:
         * 1 -> user 1
         * 2 -> user 2
         * 3 -> top title
         * 4 -> bottom title
         */
        viewModel.change.observe(viewLifecycleOwner){ index ->
            when(index){
                1 -> {
                    viewModel.userInfo1.observe(viewLifecycleOwner){user->
                        binding.editText.setText(user.nickName)
                        binding.btnOk.setOnClickListener {
                            if(binding.editText.text.toString() == ""){
                                Toast.makeText(requireContext(), "Name is empty!", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                user.nickName = binding.editText.text.toString()
                                viewModel.setUserInfo1(user)
                            }
                            dismiss()
                        }
                    }
                }
                2 -> {
                    viewModel.userInfo2.observe(viewLifecycleOwner){user->
                        binding.editText.setText(user.nickName)
                        binding.btnOk.setOnClickListener {
                            if(binding.editText.text.toString() == ""){
                                Toast.makeText(requireContext(), "Name is empty!", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                user.nickName = binding.editText.text.toString()
                                viewModel.setUserInfo2(user)
                            }
                            dismiss()
                        }
                    }
                }
                3 -> {
                    viewModel.loveDate.observe(viewLifecycleOwner){loveDate ->
                        binding.editText.setText(loveDate.topTitle)
                        binding.btnOk.setOnClickListener {
                            if(binding.editText.text.toString() == ""){
                                Toast.makeText(requireContext(), "Top title is empty!", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                loveDate.topTitle = binding.editText.text.toString()
                                viewModel.setLoveDate(loveDate)
                            }
                            dismiss()
                        }
                    }
                }
                4 -> {
                    viewModel.loveDate.observe(viewLifecycleOwner){loveDate ->
                        binding.editText.setText(loveDate.bottomTitle)
                        binding.btnOk.setOnClickListener {
                            if(binding.editText.text.toString() == ""){
                                Toast.makeText(requireContext(), "Top title is empty!", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                loveDate.bottomTitle = binding.editText.text.toString()
                                viewModel.setLoveDate(loveDate)
                            }
                            dismiss()
                        }
                    }
                }
            }
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}