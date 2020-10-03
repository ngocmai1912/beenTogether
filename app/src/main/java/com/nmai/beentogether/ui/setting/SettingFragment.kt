package com.nmai.beentogether.ui.setting

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentSettingBinding
import com.nmai.beentogether.repository.Data
import com.nmai.beentogether.ui.home.HomeViewModel
import java.util.*
import java.util.concurrent.TimeUnit

class SettingFragment : Fragment() {
    lateinit var binding : FragmentSettingBinding
    private val viewModel : HomeViewModel by activityViewModels()
    private val settingViewModel : SettingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loveDate.observe(viewLifecycleOwner){loveDate->
            val bitmap = Data.stringToBitmap(loveDate.wallpaper)
            val bg = BitmapDrawable(resources, bitmap)
            binding.settingLayout.background = bg
        }
        /**
         * back
         */
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_homeFragment)
        }
        /**
         * privacy
         */
        binding.privacy.setOnClickListener {
            PrivacyFragment().show(childFragmentManager, "privacy")
        }
        /**
         * rate
         */
        binding.rate.setOnClickListener {
            RatingFragment().show(childFragmentManager, "rating")
        }
        /**
         * notification_layout
         */
        // check switch button
        var sharePrf = requireContext().getSharedPreferences("switch notification", Context.MODE_PRIVATE)
        var x = sharePrf.getInt("switch notification", 0)
        if(x == 0){
            binding.switchNotify.isChecked = false
            binding.switchNotify.thumbDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.thumb))
            binding.switchNotify.trackDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.track))
        }
        else{
            binding.switchNotify.isChecked = true
            binding.switchNotify.thumbDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.colorUser4))
            binding.switchNotify.trackDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.colorUser1))
        }
        val notification = Notification(requireContext(), "1")
        notification.createNotificationChannel()
        viewModel.loveDate.observe(viewLifecycleOwner){loveDate ->
            viewModel.userInfo1.observe(viewLifecycleOwner){us1 ->
                viewModel.userInfo2.observe(viewLifecycleOwner){us2->
                    var day = Calendar.getInstance().timeInMillis - Data.changeStringToDate(loveDate.startDate).time
                    val date = (TimeUnit.MILLISECONDS.toDays(day)+1).toString()
                    notification.creatNotification(us1.nickName, us2.nickName, us1.gender, us2.gender, date)
                }
            }
        }
        binding.switchNotify.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, isCheck: Boolean) {
                if(isCheck == false){
                    binding.switchNotify.thumbDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.thumb))
                    binding.switchNotify.trackDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.track))
                    with(sharePrf.edit()){
                        putInt("switch notification", 0)
                        commit()
                    }
                    notification.stopNotification()
                }
                else{
                    binding.switchNotify.thumbDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.colorUser4))
                    binding.switchNotify.trackDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.colorUser1))
                    with(sharePrf.edit()){
                        putInt("switch notification", 1)
                        commit()
                    }
                    viewModel.loveDate.observe(viewLifecycleOwner){loveDate ->
                        viewModel.userInfo1.observe(viewLifecycleOwner){us1 ->
                            viewModel.userInfo2.observe(viewLifecycleOwner){us2->
                                var day = Calendar.getInstance().timeInMillis - Data.changeStringToDate(loveDate.startDate).time
                                val date = (TimeUnit.MILLISECONDS.toDays(day)+1).toString()
                                notification.creatNotification(us1.nickName, us2.nickName, us1.gender, us2.gender, date)
                            }
                        }
                    }
                }
            }

        })

        /**
         * notification quote
         */
        binding.switchQuote.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, isCheck: Boolean) {
                if(isCheck == false){
                    binding.switchQuote.thumbDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.thumb))
                    binding.switchQuote.trackDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.track))

                }
                else{
                    binding.switchQuote.thumbDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.colorUser4))
                    binding.switchQuote.trackDrawable.setTint(ContextCompat.getColor(requireContext(), R.color.colorUser1))

                }
            }

        })
    }
}