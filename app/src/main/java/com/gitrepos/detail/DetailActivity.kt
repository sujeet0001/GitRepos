package com.gitrepos.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gitrepos.R
import com.gitrepos.databinding.ActivityDetailBinding
import com.gitrepos.domain.main.entity.ListEntity
import com.gitrepos.utils.viewModelProvider
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.header.view.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = viewModelProvider(viewModelFactory)

        getData()
        initViews()
    }

    private fun getData(){
        viewModel.listEntity = intent.extras?.getSerializable("ListEntity") as ListEntity
    }

    private fun initViews(){

        binding.header.root.tv_header.text = "Details"

        binding.tvTitle.text = viewModel.listEntity.owner!!.login

        binding.tvSub.text = viewModel.listEntity.name

        Picasso.get().load(viewModel.listEntity.owner!!.avatar_url).into(binding.ivAvatar)

    }
}