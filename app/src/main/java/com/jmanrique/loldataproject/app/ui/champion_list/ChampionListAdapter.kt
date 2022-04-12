package com.jmanrique.loldataproject.app.ui.champion_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.loldataproject.R
import com.jmanrique.loldataproject.databinding.ItemChampionSummaryBinding
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.utils.extensions.getAvatarUrl
import com.jmanrique.loldataproject.utils.extensions.loadUrl
import javax.inject.Inject

class ChampionListAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<ChampionListAdapter.ViewHolder>() {

    var data: List<ChampionSummary> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemChampionSummaryBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: ChampionListAdapter.ViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemChampionSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champion: ChampionSummary) {
            binding.championListAvatar.loadUrl(getAvatarUrl(champion.id.toString()), R.drawable.champion_avatar_placeholder)
            binding.championListName.text = champion.name
        }
    }


}