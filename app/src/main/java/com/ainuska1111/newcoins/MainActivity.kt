package com.ainuska1111.newcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ainuska1111.newcoins.adapter.CoinAdapter
import com.ainuska1111.newcoins.data.network.model.Status
import com.ainuska1111.newcoins.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initObservers()
        initClickListeners()
    }

    private fun initObservers() {
        viewModel?.coinLiveData?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progress.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progress.isVisible = false
                    val data = it.data
                    if (data != null) {
                        binding.recyclerView.adapter = CoinAdapter(data.coins)
                    }
                }
                Status.ERROR -> {
                    binding.progress.isVisible = false
                    Toast.makeText( this, it.message, Toast.LENGTH_LONG).show()
                }
            }

        })


    }

    private fun initClickListeners() {
        /* val intent = Intent(context, Fragment::class.java)
         binding.item_recycler.setOnClickListener {
             intent .putExtra()
             startActivity(intent )*/

    }


}
