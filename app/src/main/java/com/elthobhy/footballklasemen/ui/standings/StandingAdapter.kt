package com.elthobhy.footballklasemen.ui.standings

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsItem
import com.elthobhy.footballklasemen.databinding.ItemStandingsLeagueBinding

class StandingAdapter: RecyclerView.Adapter<StandingAdapter.StandingViewHolder>(){
    private val list = ArrayList<StandingsItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StandingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_standings_league, parent, false)
        return StandingViewHolder(view)
    }

    inner class StandingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemStandingsLeagueBinding.bind(itemView)
        fun bind(entity: StandingsItem) {
            binding.apply {

                Glide.with(itemView)
                    .load(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/f/f0/Error.svg"))
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(imageStanding)
                nameStanding.text = entity.team?.shortDisplayName
                gpStanding.text = entity.stats?.get(3)?.value.toString() //3
                dStanding.text = entity.stats?.get(2)?.value.toString()  //2
                wStanding.text = entity.stats?.get(0)?.value.toString()  //0
                lStanding.text = entity.stats?.get(1)?.value.toString()  //1
                gaStanding.text = entity.stats?.get(5)?.value.toString() //5
                gfStanding.text = entity.stats?.get(4)?.value.toString() //4
                gaRank.text = entity.stats?.get(8)?.value.toString()     //8
            }
        }
    }

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        list[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setList(entity: List<StandingsItem>?){
        list.clear()
        if (entity != null) {
            list.addAll(entity)
        }
        Log.e("StandingAdapter", "setList: ${entity?.size}" )
        notifyDataSetChanged()
    }
}