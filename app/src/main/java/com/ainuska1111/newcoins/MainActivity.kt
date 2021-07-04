package com.ainuska1111.newcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        initView()
        initObservers()
        initClickListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.loadCoin("USD")
    }

    private fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

    }

    private fun initObservers() {
        viewModel?.coinLiveData?.observe(this, Observer {
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
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }

        })


    }

    private fun initClickListeners() {
        val intent = Intent(context, CoinFragment::class.java)
        binding.recyclerView.setOnClickListener {
            intent.putExtra(Extras.HOME_ALL, Type.COINS.name)
            startActivity(intent)
        }
    }

    private fun displayData(response: HomeResponse?) {
        binding.recyclerView.adapter = CoinAdapter(response?.coin
                ?: listOf(), this, R.layout.item_coins)


        if (response?.coin.isNullOrEmpty()) {
            binding.recyclerView.isVisible = false
        }

    }

