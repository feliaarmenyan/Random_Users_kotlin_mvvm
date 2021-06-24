package com.randomusers_kotlin_mvvm.ui.userdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.randomusers_kotlin_mvvm.R
import com.randomusers_kotlin_mvvm.core.platform.viewbindingdelegate.viewBinding
import com.randomusers_kotlin_mvvm.core.utils.displayWidth
import com.randomusers_kotlin_mvvm.core.utils.loadImage
import com.randomusers_kotlin_mvvm.databinding.UserDetailsFragmentBinding

class UserDetailsFragment : Fragment(R.layout.user_details_fragment) {

    private val mBinding by viewBinding(UserDetailsFragmentBinding::bind)

    private val args by navArgs<UserDetailsFragmentArgs>()


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mBinding) {
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
            val width = ((requireContext().displayWidth()))
            userImage.layoutParams.width = width

            userImage.loadImage(
                args.user.picture?.large,
            )
            toolbar.title = args.user.name?.first + " " + args.user.name?.last
            userTitle.text =
                args.user.name?.first + " " + args.user.name?.last + " (" + args.user.gender + ")"

            userAddressDescription.text =
                args.user.location?.state + " "  + args.user.location?.city

            userEmailDescription.text = args.user.email
            userPhoneNumberDescription.text = args.user.phone
            userAgeDescription.text = args.user.dob?.age.toString()
        }
    }
}