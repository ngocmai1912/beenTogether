package com.nmai.beentogether.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nmai.beentogether.R
import com.nmai.beentogether.databinding.FragmentHomeBinding
import com.nmai.beentogether.repository.Data
import com.nmai.beentogether.repository.lovedate.LoveDate
import com.nmai.beentogether.repository.lovedate.LoveDateDatabase
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.ui.home.user.avataruser.AvatarUserDialogFragment
import com.nmai.beentogether.ui.home.choosecolor.ChooseColorDialogFragment
import com.nmai.beentogether.ui.home.lovedate.LoveDateDialogFragment
import com.nmai.beentogether.ui.home.lovedate.LoveDateFactory
import com.nmai.beentogether.ui.home.lovedate.LoveDateViewModel
import com.nmai.beentogether.ui.home.lovequote.LoveQuoteDialogFragment
import com.nmai.beentogether.ui.home.user.UserFactory
import com.nmai.beentogether.ui.home.user.UserViewModel
import com.nmai.beentogether.ui.home.user.nameuser.NameUserDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var loveD : LoveDate
    lateinit var us1 : User
    lateinit var us2 : User
    private val viewModel : HomeViewModel by activityViewModels()
    private val loveDateViewModel by viewModels<LoveDateViewModel>(factoryProducer = {
        LoveDateFactory(requireContext())
    })
    private val userViewModel by viewModels<UserViewModel>(factoryProducer = {
        UserFactory(requireContext())
    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * set view ban dau
//         */
//        //view love date
        loveDateViewModel.getData().observe(viewLifecycleOwner){loveDate->
            if(loveDate != null){
                viewModel.setLoveDate(loveDate)
            }
            else{
                Thread{
                    loveDateViewModel.insert(
                        LoveDate(
                            1,
                            Data.changeDateToString(Calendar.getInstance().time),
                            "In love",
                            "Days",
                            Data.bitmapToString(BitmapFactory.decodeResource(resources, R.drawable.img_background_default)),
                            0
                        )
                    )
                }.start()
            }
        }
        viewModel.loveDate.observe(viewLifecycleOwner){loveDate->
            loveD = loveDate
            val bitmap  = Data.stringToBitmap(loveDate.wallpaper)
            val bg = BitmapDrawable(resources, bitmap)
            binding.homeLayout.background = bg
            binding.topTitle.text = loveDate.topTitle
            binding.bottomTitle.text = loveDate.bottomTitle
            var day = Calendar.getInstance().timeInMillis - Data.changeStringToDate(loveDate.startDate).time
            binding.loveDate.text = (TimeUnit.MILLISECONDS.toDays(day)+1).toString()
            binding.borderLoveLayout.setImageResource(Data.listColor[loveDate.loveColor].code)
        }
//        //view user 1
        userViewModel.getUser1().observe(viewLifecycleOwner){user->
            if(user != null){
                viewModel.setUserInfo1(user)
            }
            else{
                Thread{
                    userViewModel.insert(
                        User(
                            1,
                            "User 1",
                            Data.bitmapToString(BitmapFactory.decodeResource(resources, R.drawable.img_user_1_default)),
                            null,
                            "female",
                            0
                        )
                    )
                }.start()
            }
        }
        viewModel.userInfo1.observe(viewLifecycleOwner){user->
            us1 = user
            user.birthday?.let { birth ->
                binding.birthUser1.text = birth
                binding.birthUser1.visibility = View.VISIBLE
            }
            binding.avaUser1.setImageBitmap(Data.stringToBitmap(user.avatar))
            binding.nickNameUser1.text = user.nickName
            binding.borderUser1.setImageResource(Data.listColor[user.borderColorCode].code)
            when(user.gender){
                "male" ->{
                    binding.genderUser1.visibility = View.VISIBLE
                    binding.genderUser1.setImageResource(R.drawable.ic_gender_male)
                }
                "female" ->{
                    binding.genderUser1.visibility = View.VISIBLE
                    binding.genderUser1.setImageResource(R.drawable.ic_gender_female)
                }
                "hide" -> binding.genderUser1.visibility = View.GONE
            }
        }
        //view user 2
        userViewModel.getUser2().observe(viewLifecycleOwner){user->
            if(user != null){
                viewModel.setUserInfo2(user)
            }
            else{
                Thread{
                    userViewModel.insert(
                        User(
                            2,
                            "User 2",
                            Data.bitmapToString(BitmapFactory.decodeResource(resources, R.drawable.img_user_2_default)),
                            null,
                            "male",
                            0
                        )
                    )
                }.start()
            }
        }
        viewModel.userInfo2.observe(viewLifecycleOwner){user->
            us2 = user
            Log.d("ksjkdksd ", user.birthday.toString())
            user.birthday?.let { birth ->
                Log.d("ksjkdksd", birth)
                binding.birthUser2.text = birth
                binding.birthUser2.visibility = View.VISIBLE
            }
            binding.avaUser2.setImageBitmap(Data.stringToBitmap(user.avatar))
            binding.nickNameUser2.text = user.nickName
            binding.borderUser2.setImageResource(Data.listColor[user.borderColorCode].code)
            when(user.gender){
                "male" ->{
                    binding.genderUser2.visibility = View.VISIBLE
                    binding.genderUser2.setImageResource(R.drawable.ic_gender_male)
                }
                "female" ->{
                    binding.genderUser2.visibility = View.VISIBLE
                    binding.genderUser2.setImageResource(R.drawable.ic_gender_female)
                }
                "hide" -> binding.genderUser2.visibility = View.GONE
            }
        }
        //navigate to setting
        binding.btnSetting.setOnClickListener {
            Thread{
                loveDateViewModel.update(loveD)
                userViewModel.update(us1)
                userViewModel.update(us2)
            }.start()
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }

        binding.borderLoveLayout.setOnClickListener {
            viewModel.setChange(0)
            val loveDateDialog = LoveDateDialogFragment()
            loveDateDialog.show(childFragmentManager, "loveDate")
        }
        binding.avaUser1.setOnClickListener {
            viewModel.setChange(1)
            AvatarUserDialogFragment().show(childFragmentManager, "user avt")
        }
        binding.avaUser2.setOnClickListener {
            viewModel.setChange(2)
            AvatarUserDialogFragment().show(childFragmentManager, "user avt")
        }
        /**
         * set view btn heart
//         */
//        var sharePrf = requireContext().getSharedPreferences("heartColor", Context.MODE_PRIVATE)
//        var index = sharePrf.getInt("heartColor", 0)
//        binding.btnHeart.background.setTint(Data.listColor[index].code)
//        binding.btnHeart.setOnClickListener {
//            viewModel.setChange(10)
//            ChooseColorDialogFragment().show(childFragmentManager, "love color")
//        }
        binding.loveQuoteLayout.setOnClickListener {
            LoveQuoteDialogFragment().show(childFragmentManager, "love quote")
        }
        binding.infoUser1.setOnClickListener {
            viewModel.setChange(1)
            NameUserDialogFragment().show(childFragmentManager, "info")
        }
        binding.infoUser2.setOnClickListener {
            viewModel.setChange(2)
            NameUserDialogFragment().show(childFragmentManager, "info")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Thread{
            loveDateViewModel.update(loveD)
            userViewModel.update(us1)
            userViewModel.update(us2)
        }.start()
    }

}