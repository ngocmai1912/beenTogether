package com.nmai.beentogether.ui.home.lovedate

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentLoveDateDialogBinding
import com.nmai.beentogether.repository.Data
import com.nmai.beentogether.repository.lovedate.LoveDate
import com.nmai.beentogether.ui.home.HomeViewModel
import com.nmai.beentogether.ui.home.user.avataruser.ChangeTextDialogFragment
import com.nmai.beentogether.ui.home.choosecolor.ChooseColorDialogFragment
import timber.log.Timber
import java.io.InputStream
import java.util.*
import kotlin.time.days

class LoveDateDialogFragment : DialogFragment() {
    lateinit var binding : FragmentLoveDateDialogBinding
    private val SELECT_IMG_REQ_CODE = 10
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoveDateDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * set background
         */
        binding.chooseBackground.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Select image"), SELECT_IMG_REQ_CODE)
        }
        /**
         * reset background
         */
        binding.resetBackground.setOnClickListener {
            viewModel.loveDate.observe(viewLifecycleOwner){loveDate ->
                loveDate.wallpaper = Data.bitmapToString(
                    BitmapFactory.decodeResource(resources, R.drawable.img_background_default)
                )
                viewModel.setLoveDate(loveDate)
            }
        }
        /**
         * change title:
         */
        binding.changeTopTitle.setOnClickListener {
            viewModel.setChange(3)
            ChangeTextDialogFragment().show(childFragmentManager, "change top title")
        }
        binding.changeBottomTitle.setOnClickListener {
            viewModel.setChange(4)
            ChangeTextDialogFragment().show(childFragmentManager, "change top title")
        }
        binding.chooseLoveColor.setOnClickListener {
            ChooseColorDialogFragment().show(childFragmentManager, "choose love color")
        }

        /**
         * set start date
         */
        viewModel.loveDate.observe(viewLifecycleOwner){loveDate->
            binding.chooseStartDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val date = Data.changeStringToDate(loveDate.startDate)
                calendar.time = date
                var day = calendar.get(Calendar.DAY_OF_MONTH)
                var month = calendar.get(Calendar.MONTH)
                var year = calendar.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(requireContext(),R.style.DialogTheme, DatePickerDialog.OnDateSetListener {
                        _, yearr, monthOfYear, dayOfMonth -> viewModel.setLoveDate(
                    LoveDate(
                        loveDate._id,
                        "${dayOfMonth}/${monthOfYear+1}/${yearr}",
                        loveDate.topTitle,
                        loveDate.bottomTitle,
                        loveDate.wallpaper,
                        loveDate.loveColor
                    )
                )
                    day = dayOfMonth
                    month = monthOfYear
                    year = yearr
                }, year, month, day).show()
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
            viewModel.loveDate.observe(viewLifecycleOwner) { loveDate ->
                loveDate.wallpaper = Data.bitmapToString(bitmap)
                viewModel.setLoveDate(loveDate)
            }
        }
    }
}