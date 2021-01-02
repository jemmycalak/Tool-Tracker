/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jemmy.calak.tooltracking.adapter.ToolsAdapter
import jemmy.calak.tooltracking.databinding.FragmentToolsBinding

@AndroidEntryPoint
class ListToolsFragment : Fragment() {

    private val listToolViewModel: ListToolsViewModel by viewModels()
    lateinit var binding: FragmentToolsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentToolsBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = listToolViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTools.apply {
            adapter = ToolsAdapter(listToolViewModel)
            setHasFixedSize(true)
        }
    }
}