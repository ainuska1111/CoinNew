package com.ainuska1111.newcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ainuska1111.newcoins.adapter.CoinAdapter
import com.ainuska1111.newcoins.data.network.model.Status
import com.ainuska1111.newcoins.databinding.ActivityMainBinding
import com.ainuska1111.newcoins.ui.CoinFragment
import com.ainuska1111.newcoins.util.Extras


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
                    val data = it.data
                    if (data != null) {
                        binding.recyclerView.adapter = CoinAdapter(data.coins, this, R.layout.item_coins)
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
        val intent = Intent(context, CoinFragment::class.java)
        binding.recyclerView.setOnClickListener {
            intent.putExtra(Extras.HOME_ALL, Type.COINS.name)
            startActivity(intent)
        }
    }

}

