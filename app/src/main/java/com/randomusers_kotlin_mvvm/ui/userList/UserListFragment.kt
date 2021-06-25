package com.randomusers_kotlin_mvvm.ui.userList

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.randomusers_kotlin_mvvm.R
import com.randomusers_kotlin_mvvm.core.platform.viewbindingdelegate.viewBinding
import com.randomusers_kotlin_mvvm.core.utils.gone
import com.randomusers_kotlin_mvvm.core.utils.visible
import com.randomusers_kotlin_mvvm.databinding.UserListFragmentBinding
import com.randomusers_kotlin_mvvm.ui.RandomUsersViewModel
import com.randomusers_kotlin_mvvm.ui.userdetails.UserDetailsFragmentArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private val mBinding by viewBinding(UserListFragmentBinding::bind)
    private val mAdapter by lazy { UserAdapter() }

    private val mViewModel by viewModel<RandomUsersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            recyclerView.adapter = mAdapter

            mAdapter.onUserItemClick = { user ->
                val args = UserDetailsFragmentArgs(user)
                findNavController().navigate(
                    R.id.action_UserListFragment_to_UserDetailsFragment,
                    args.toBundle()
                )
            }

            mAdapter.onUserPhoneItemClick = { user ->
                try {
                    val u = Uri.parse("tel:" + user.phone)
                    val i = Intent(Intent.ACTION_DIAL, u)
                    startActivity(i)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            lifecycleScope.launch {
                mViewModel.users.collectLatest {
                    mAdapter.submitData(it)
                }
            }

            lifecycleScope.launchWhenResumed {
                mAdapter.loadStateFlow.collectLatest { loadStates ->
                    when (loadStates.source.refresh) {
                        is LoadState.NotLoading -> {
                            recyclerView.visible(false)
                        }
                        is LoadState.Error -> {
                            Timber.e((loadStates.refresh as LoadState.Error).error)
                            recyclerView.gone(false)
                        }
                    }
                }
            }
        }
    }
}