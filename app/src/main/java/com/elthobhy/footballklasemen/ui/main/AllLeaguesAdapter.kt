package com.elthobhy.footballklasemen.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.databinding.ListItemAllLeagueBinding

class AllLeaguesAdapter: RecyclerView.Adapter<AllLeaguesAdapter.AllLeaguesViewHolder>(){
    private val list = ArrayList<AllLeagues>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllLeaguesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_all_league, parent, false)
        return AllLeaguesViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: AllLeagues)
    }

    inner class AllLeaguesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ListItemAllLeagueBinding.bind(itemView)
        fun bind(entity: AllLeagues) {
            binding.apply {
                Glide.with(itemView)
                    .load(entity.logos?.dark)
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(ImageAllLeagueName)
                tvAllLeagueName.text = entity.abbr
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(entity)
            }
        }
    }

    override fun onBindViewHolder(holder: AllLeaguesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    fun setList(headlineEntity: List<AllLeagues>){
        list.addAll(headlineEntity)
        notifyDataSetChanged()
    }
}