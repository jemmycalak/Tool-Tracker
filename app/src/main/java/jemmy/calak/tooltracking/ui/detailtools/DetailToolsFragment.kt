/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.detailtools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jemmy.calak.tooltracking.adapter.UsersAdapter
import jemmy.calak.tooltracking.databinding.DetailToolsFragmentBinding
import jemmy.calak.tooltracking.ui.friends.FriendsViewModel

@AndroidEntryPoint
class DetailToolsFragment : Fragment() {

    private val detailToolViewModel: DetailToolsViewModel by viewModels()
    private val friendViewModel: FriendsViewModel by viewModels()
    lateinit var binding: DetailToolsFragmentBinding
    val models: DetailToolsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DetailToolsFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = detailToolViewModel
            friendsViewModel = friendViewModel
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.title = models.data.nameTool
        binding.rvBorrower.apply {
            adapter = UsersAdapter(friendViewModel)
            setHasFixedSize(true)
        }
        detailToolViewModel.getTools(models.data.nameTool)
        detailToolViewModel.getListTools(models.data.nameTool)
    }

}
