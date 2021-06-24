package com.randomusers_kotlin_mvvm.ui.userList

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
import com.randomusers_kotlin_mvvm.databinding.UserDetailsFragmentBinding.bind
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

            mAdapter.onUserItemClick = { user, pos ->
                val args = UserDetailsFragmentArgs(user)
                findNavController().navigate(
                    R.id.action_UserListFragment_to_UserDetailsFragment,
                  args.toBundle()
                )
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