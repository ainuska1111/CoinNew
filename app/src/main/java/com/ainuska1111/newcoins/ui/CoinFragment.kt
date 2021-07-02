package com.ainuska1111.newcoins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ainuska1111.newcoins.R
import com.ainuska1111.newcoins.adapter.CoinAdapter
import com.ainuska1111.newcoins.databinding.FragmentBinding
import com.ainuska1111.newcoins.util.Extras

class CoinFragment : Fragment(R.layout.fragment) {
    private var _binding: FragmentBinding? = null
    private val binding get() = _binding!!

    var viewModel: CoinViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        initObservers()
        val type = intent.getStringExtra(Extras.HOME_ALL)
        type?.let {
            loadData(it)
        }
    }

    private fun initObservers() {
        viewModel?.coinLiveData?.observe(viewLifecycleOwner) {
            binding.recycler.adapter = CoinAdapter(it.productList, this, R.layout.fragment_item)
        }
        viewModel?.errorLiveData?.observe(viewLifecycleOwner) {

        }
    }

    private fun loadData(type: String) {
        viewModel?.loadData(type)
    }
}









