package com.elthobhy.footballklasemen.ui.detail

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.databinding.ListItemSeasonBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import    java.time.format.DateTimeFormatter

class SeasonAdapter : RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {
    private val list = ArrayList<SeasonLeague>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeasonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_season, parent, false)
        return SeasonViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: SeasonLeague)
    }

    inner class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemSeasonBinding.bind(itemView)
        fun bind(entity: SeasonLeague) {
            val stringStart = entity.startDate?.split('T')
            val dateStart = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.parse(stringStart?.get(0), DateTimeFormatter.ISO_DATE)
            } else {
                entity.startDate
            }
            val stringEnd = entity.endDate?.split('T')
            val dateEnd = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.parse(stringEnd?.get(0), DateTimeFormatter.ISO_DATE)
            } else {
                entity.endDate
            }

            binding.apply {
                seasonYearItem.text = entity.year.toString()
                seasonNameItem.text = entity.displayName
                startDateItem.text = dateStart.toString()
                endDateItem.text = dateEnd.toString()
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(entity)
            }
        }
    }


    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(entity: List<SeasonLeague>) {
        list.addAll(entity)
        notifyDataSetChanged()
    }
}