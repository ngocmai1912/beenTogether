package com.nmai.beentogether.ui.home.choosecolor

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentChooseColorDialogBinding
import com.nmai.beentogether.repository.Data
import com.nmai.beentogether.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class ChooseColorDialogFragment : DialogFragment() {
    lateinit var binding : FragmentChooseColorDialogBinding
    lateinit var adapterColor : ColorAdapter
    private val viewModel : HomeViewModel  by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("najshdjbs")
        binding = FragmentChooseColorDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.change.observe(viewLifecycleOwner){index ->

            binding.btnCancel.setOnClickListener {
                dismiss()
            }
            when(index){
                1 -> {
                    viewModel.userInfo1.observe(viewLifecycleOwner){user ->
                        adapterColor = ColorAdapter(requireContext(), Data.listColor[user.borderColorCode])
                        adapterColor.update()
                        binding.recyListColor.apply {
                            adapter = adapterColor
                            layoutManager = GridLayoutManager(requireContext(), 4)
                        }
                        binding.btnOk.setOnClickListener {
                            user.borderColorCode = Data.listColor.indexOf(adapterColor.currentColorCode)
                            viewModel.setUserInfo1(user)
                            dismiss()
                        }
                    }
                }
                2 -> {
                    viewModel.userInfo2.observe(viewLifecycleOwner){user ->
                        adapterColor = ColorAdapter(requireContext(), Data.listColor[user.borderColorCode])
                        adapterColor.update()
                        binding.recyListColor.apply {
                            adapter = adapterColor
                            layoutManager = GridLayoutManager(requireContext(), 4)
                        }
                        binding.btnOk.setOnClickListener {
                            user.borderColorCode = Data.listColor.indexOf(adapterColor.currentColorCode)
                            viewModel.setUserInfo2(user)
                            dismiss()
                        }
                    }
                }
                10 -> {
                    var sharePrf = requireContext().getSharedPreferences("heartColor", Context.MODE_PRIVATE)
                    var index = sharePrf.getInt("heartColor", 0)
                    adapterColor = ColorAdapter(requireContext(), Data.listColor[index])
                    adapterColor.update()
                    binding.recyListColor.apply {
                        adapter = adapterColor
                        layoutManager = GridLayoutManager(requireContext(), 4)
                    }
                    binding.btnOk.setOnClickListener {
                        with(sharePrf.edit()){
                            var x = Data.listColor.indexOf(adapterColor.currentColorCode)
                            putInt("heartColor", x)
                            commit()
                        }
                        dismiss()
                    }
                }
                else -> {
                    viewModel.loveDate.observe(viewLifecycleOwner){loveDate ->
                        adapterColor = ColorAdapter(requireContext(), Data.listColor[loveDate.loveColor])
                        adapterColor.update()
                        binding.recyListColor.apply {
                            adapter = adapterColor
                            layoutManager = GridLayoutManager(requireContext(), 4)
                        }

                        binding.btnOk.setOnClickListener {
                            loveDate.loveColor = Data.listColor.indexOf(adapterColor.currentColorCode)
                            viewModel.setLoveDate(loveDate)
                            dismiss()
                        }
                    }
                }
            }
        }
    }
}