/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jemmy.calak.tooltracking.adapter.UsersAdapter
import jemmy.calak.tooltracking.databinding.FragmentFriendsBinding

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    private val friendViewModel: FriendsViewModel by viewModels()
    lateinit var binding: FragmentFriendsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return FragmentFriendsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            binding = this
            viewModel = friendViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFriends.apply {
            adapter = UsersAdapter(friendViewModel)
            setHasFixedSize(true)
        }
    }
}
