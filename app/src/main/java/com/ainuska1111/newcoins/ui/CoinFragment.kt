package com.ainuska1111.newcoins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ainuska1111.newcoins.R
import com.ainuska1111.newcoins.data.network.model.CoinResponse
import com.ainuska1111.newcoins.databinding.FragmentBinding

class CoinFragment : Fragment(R.layout.fragment) {
    private var _binding: FragmentBinding? = null
    private val binding get() = _binding!!

    var viewModel: CoinViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        //initObservers()
    }


}

/* private fun initObservers() {
     viewModel?.coinLiveData?.observe(viewLifecycleOwner, {
         when (it.status) {
             Status.LOADING -> {
                 binding.progress.isVisible = true
             }
             Status.SUCCESS -> {
                 binding.progress.isVisible = false
                 displayData(it.data)
             }
             Status.ERROR -> {
                 binding.progress.isVisible = false
                 Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
             }
         }
     })*/



private fun displayData(response: CoinResponse?) {

}