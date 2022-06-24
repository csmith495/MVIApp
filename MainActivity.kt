package com.example.mvianimals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvianimals.api.AnimalService
import com.example.mvianimals.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = AnimalListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupObservables()
    }

    private fun setupUI() {
        mainViewModel = ViewModelProvider(this, ViewModelFactory(AnimalService.api))
            .get(MainViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
        btnFetchAnimals.setOnClickListener {
            lifecycleScope.launch {
                // send the mainintent to change the state
                // and attempt to retrieve data from the api
                mainViewModel.userIntent.send(MainIntent.FetchAnimals)
            }
        }
    }

    // observe the state of the app
    private fun setupObservables() {
        lifecycleScope.launch {
            mainViewModel.state.collect() { collector ->
                when(collector) {
                    is MainState.idle -> {

                    }
                    is MainState.loading -> {
                        btnFetchAnimals.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Animals -> {
                        progressBar.visibility = View.GONE
                        btnFetchAnimals.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        collector.animals.let {
                            adapter.newAnimals(it)
                        }
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        btnFetchAnimals.visibility = View.GONE
                        Toast.makeText(this@MainActivity, collector.error, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}