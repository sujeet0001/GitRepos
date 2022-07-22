package com.gitrepos.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gitrepos.R
import com.gitrepos.databinding.ItemListBinding
import com.gitrepos.domain.main.entity.ListEntity
import com.squareup.picasso.Picasso

class MainAdapter(private val listItems: List<ListEntity>):
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        val listEntity: ListEntity = listItems[position]
        holder.b.tvTitle.text = listEntity.name
        holder.b.tvSub.text = listEntity.owner!!.login
        Picasso.get().load(listEntity.owner.avatar_url).into(holder.b.ivAvatar)

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class MainHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val b: ItemListBinding = binding
    }

}