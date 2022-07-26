package com.gitrepos.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gitrepos.R
import com.gitrepos.databinding.ItemListBinding
import com.gitrepos.detail.DetailActivity
import com.gitrepos.domain.main.entity.ListEntity
import com.squareup.picasso.Picasso

class MainAdapter(private val listItems: List<ListEntity>):
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {

        ctx = parent.context

        return MainHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.item_list, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val listEntity: ListEntity = listItems[position]
        holder.b.tvTitle.text = listEntity.owner!!.login
        holder.b.tvSub.text = listEntity.name
        Picasso.get().load(listEntity.owner.avatar_url).into(holder.b.ivAvatar)

        holder.b.root.setOnClickListener{
            val i: Intent = Intent(ctx, DetailActivity::class.java)
            i.putExtra("ListEntity", listEntity)
            ctx.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class MainHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val b: ItemListBinding = binding

    }

}