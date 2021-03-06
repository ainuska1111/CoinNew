package com.ainuska1111.newcoins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ainuska1111.newcoins.R
import com.ainuska1111.newcoins.adapter.CoinAdapter
import com.ainuska1111.newcoins.data.network.model.CoinResponse
import com.ainuska1111.newcoins.databinding.FragmentBinding
import com.ainuska1111.newcoins.util.Extras

class CoinFragment : Fragment(R.layout.fragment) {
    private var _binding: FragmentBinding? = null
    private val binding get() = _binding!!

    private var viewModel: CoinViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        initObservers()
        val type = intent.getStringExtra(Extras.HOME_ALL)
        type.let {
            loadData(type)
        }
    }

    private fun initObservers() {
        viewModel?.coinLiveData?.observe(viewLifecycleOwner) {
            binding.recycler.adapter = CoinAdapter(it.coins?, this, R.layout.fragment_item)

        }

    }

    private fun loadData(type: String) {
        viewModel?.loadData(type)
    }
}


