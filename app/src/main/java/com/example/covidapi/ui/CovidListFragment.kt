package com.example.covidapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.covidapi.R
import com.example.covidapi.databinding.FragmentCovidListBinding

class CovidListFragment: Fragment() {
    private val viewModel: CovidViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCovidListBinding.inflate(inflater)
        viewModel.getGameList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = CovidListAdapter(GameListener { game ->
            viewModel.onGameClicked(game)
            findNavController()
                .navigate(R.id.action_CovidListFragment_to_CovidDetailFragment)
        })

        (activity as AppCompatActivity).supportActionBar?.title = "Penyebaran Covid di Indonesia"

        return binding.root
    }
}