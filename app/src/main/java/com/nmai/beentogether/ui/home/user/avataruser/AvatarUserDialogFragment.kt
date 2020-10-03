package com.nmai.beentogether.ui.home.user.avataruser

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentAvatarUserDialogBinding
import com.nmai.beentogether.repository.Data
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.ui.home.HomeViewModel
import com.nmai.beentogether.ui.home.choosecolor.ChooseColorDialogFragment
import java.io.DataInput
import java.io.InputStream
import java.util.*

class AvatarUserDialogFragment : DialogFragment() {
    lateinit var binding : FragmentAvatarUserDialogBinding
    private val SELECT_IMG_REQ_CODE = 10
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarUserDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * change avatar
         */
        binding.chooseAvt.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Select image"), SELECT_IMG_REQ_CODE)
        }

        /**
         * reset avatar
         */
        binding.resetAvt.setOnClickListener {
            viewModel.change.observe(viewLifecycleOwner){index ->
                when(index){
                    1 -> {
                        viewModel.userInfo1.observe(viewLifecycleOwner){user->
                            user.avatar = Data.bitmapToString(
                                BitmapFactory.decodeResource(resources, R.drawable.img_user_1_default)
                            )
                            viewModel.setUserInfo1(user)
                        }
                    }
                    2 -> {
                        viewModel.userInfo2.observe(viewLifecycleOwner){user->
                            user.avatar = Data.bitmapToString(
                                BitmapFactory.decodeResource(resources, R.drawable.img_user_2_default)
                            )
                            viewModel.setUserInfo2(user)
                        }
                    }
                }
            }
        }
        binding.changeName.setOnClickListener {
            ChangeTextDialogFragment().show(childFragmentManager, "change nick name")
        }
        binding.chooseBorderColor.setOnClickListener {
            ChooseColorDialogFragment().show(childFragmentManager, "choose border color")
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
                            val datePickerDialog = DatePickerDialog(requireContext(),R.style.DialogTheme, DatePickerDialog.OnDateSetListener {
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SELECT_IMG_REQ_CODE && data != null) {
            val inputStream : InputStream? = data?.data?.let {
                requireContext().contentResolver.openInputStream(it)
            }
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            viewModel.change.observe(viewLifecycleOwner){index ->
                when(index){
                    1 -> {
                        viewModel.userInfo1.observe(viewLifecycleOwner){user->
                            user.avatar = Data.bitmapToString(bitmap)
                            viewModel.setUserInfo1(user)
                        }
                    }
                    2 -> {
                        viewModel.userInfo2.observe(viewLifecycleOwner){user->
                            user.avatar = Data.bitmapToString(bitmap)
                            viewModel.setUserInfo2(user)
                        }
                    }
                }
            }
        }
    }
}