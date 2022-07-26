package com.gitrepos.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitrepos.R
import com.gitrepos.databinding.ActivityMainBinding
import com.gitrepos.domain.main.entity.ListEntity
import com.gitrepos.utils.observeEvent
import com.gitrepos.utils.viewModelProvider
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = viewModelProvider(viewModelFactory)

        setViews()
        setLayoutManager()
        setListeners()
        setObservers()

    }

    private fun setViews() {
        binding.toolbar.tvHeader.text = "Repositories"
    }

    private fun setLayoutManager() {
        binding.lvList.setHasFixedSize(true)
        binding.lvList.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun setListAdapter(list: List<ListEntity>) {
        mainAdapter = MainAdapter(list)
        binding.lvList.adapter = mainAdapter
    }

    private fun setListeners() {
        binding.errorLayout.btnRetry.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            viewModel.getRepos("oktokit")
        }
    }

    private fun setObservers() {

        viewModel.listEntity.observeEvent(
            this, this::onSuccess, this::onFailure,
            this::onLoading
        )

    }

    private fun onSuccess(list: List<ListEntity>) {
        if (list.isNotEmpty()) {
            setListAdapter(list)
            binding.lvList.visibility = View.VISIBLE
        } else {
            Log.d("mydata", "No repositories found")
        }

    }

    private fun onFailure(t: Throwable) {
        binding.errorLayout.root.visibility = View.VISIBLE
    }

    private fun onLoading(bool: Boolean) {
        if (bool) {
            binding.pbLoader.visibility = View.VISIBLE
        } else {
            binding.pbLoader.visibility = View.GONE
        }
    }

}