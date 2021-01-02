/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.detailfriends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jemmy.calak.tooltracking.adapter.TransactionAdapter
import jemmy.calak.tooltracking.databinding.FriendDetailFragmentBinding
import jemmy.calak.tooltracking.ui.transaction.TransactionViewModel


@AndroidEntryPoint
class FriendDetailFragment : Fragment() {

    companion object {
        val TAG = FriendDetailFragment::class.simpleName
    }

    lateinit var binding: FriendDetailFragmentBinding
    private val friendDetailViewModel: FriendDetailViewModel by viewModels()
    private val transactionViewModels: TransactionViewModel by viewModels()
    val model: FriendDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FriendDetailFragmentBinding.inflate(inflater, container, false).apply {
            binding = this
            viewModel = friendDetailViewModel
            transactionViewModel = transactionViewModels
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.title = model.data.name
        binding.rvBorrower.apply {
            adapter = TransactionAdapter(TAG, transactionViewModels)
            setHasFixedSize(true)
        }

        friendDetailViewModel.getUser(model.data.name)
        transactionViewModels.getListTransactionByUserId(model.data.name)
    }

}
