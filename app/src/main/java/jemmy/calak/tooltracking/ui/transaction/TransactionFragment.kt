/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jemmy.calak.tooltracking.adapter.TransactionAdapter
import jemmy.calak.tooltracking.databinding.FragmentTransactionsBinding

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private val transactionViewModel: TransactionViewModel by viewModels()
    lateinit var binding: FragmentTransactionsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return FragmentTransactionsBinding.inflate(inflater, container, false).apply {
            binding = this
            viewModel = transactionViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTransaction.apply {
            adapter = TransactionAdapter(viewModel = transactionViewModel)
            setHasFixedSize(true)
        }

        transactionViewModel.getListTransaction()
    }
}
