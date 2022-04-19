package com.jmanrique.loldataproject.app.ui.champion_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.loldataproject.R
import com.jmanrique.loldataproject.databinding.ItemChampionSummaryBinding
import com.jmanrique.loldataproject.databinding.ItemChampionSummaryInfoBinding
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.utils.extensions.getAvatarUrl
import com.jmanrique.loldataproject.utils.extensions.loadUrl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChampionListAdapter @AssistedInject constructor(@ApplicationContext val context: Context, @Assisted var numberColumns: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class CHAMPION_SUMMARY_VIEW_TYPE(val value: Int) {
        CHAMPION(0),
        INFO(1)
    }

    var data: MutableList<ChampionSummary> = mutableListOf()
    var onItemClick: ((Int, ChampionSummary) -> Unit)? = null
    var championInfoOpened = -1
    var infoOpened = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CHAMPION_SUMMARY_VIEW_TYPE.CHAMPION.value -> {
                return ChampionViewHolder(
                    ItemChampionSummaryBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
            CHAMPION_SUMMARY_VIEW_TYPE.INFO.value -> {
                return InfoViewHolder(
                    ItemChampionSummaryInfoBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Bad viewTypeHolder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].isInfo) {
            CHAMPION_SUMMARY_VIEW_TYPE.INFO.value
        } else {
            CHAMPION_SUMMARY_VIEW_TYPE.CHAMPION.value
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            CHAMPION_SUMMARY_VIEW_TYPE.CHAMPION.value -> {
                holder as ChampionViewHolder
                holder.bind(data[position])
                holder.itemView.setOnClickListener {
                    onItemClick?.invoke(position, data[position])
                }
            }
            CHAMPION_SUMMARY_VIEW_TYPE.INFO.value -> {
                holder as InfoViewHolder
                holder.bind(data[position])
            }
        }

    }

    override fun getItemCount(): Int = data.size

    inner class ChampionViewHolder(private val binding: ItemChampionSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champion: ChampionSummary) {
            binding.championListAvatar.loadUrl(
                getAvatarUrl(champion.id.toString()),
                R.drawable.champion_avatar_placeholder
            )
            binding.championListName.text = champion.name
        }
    }

    inner class InfoViewHolder(private val binding: ItemChampionSummaryInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champion: ChampionSummary) {
            binding.championInfoName.text = "${champion.name} - ${champion.title}"
            binding.championInfoShortBio.text = champion.shortBio
        }
    }

    fun addInfoElement(position: Int, champion: ChampionSummary) {
        val championPosition =
            if (infoOpened != -1 && infoOpened < position) position - 1 else position
        if (infoOpened != -1) {
            data.removeAt(infoOpened)
            infoOpened = -1
        }
        val newPosition = ((championPosition / numberColumns) + 1) * numberColumns
        if (champion.showInfo) {
            championInfoOpened = -1
            champion.showInfo = false
        } else {
            if (championInfoOpened != -1) data[championInfoOpened].showInfo = false
            championInfoOpened = championPosition
            champion.showInfo = true
            infoOpened = newPosition
//            data.add(
//                newPosition,
//                ChampionSummary(
//                    id = champion.id,
//                    alias = champion.alias,
//                    name = champion.name,
//                    roles = champion.roles,
//                    squarePortraitPath = champion.squarePortraitPath,
//                    showInfo = true,
//                    isInfo = true,
//                    title = champion.title,
//                    shortBio = champion.shortBio
//                )
//            )
        }
    }

    fun addChampionInfo(champion: ChampionDetail){
        data[championInfoOpened].title = champion.title
        data[championInfoOpened].shortBio = champion.shortBio

        data.add(
            infoOpened,
            ChampionSummary(
                id = champion.id,
                alias = champion.alias,
                name = champion.name,
                roles = champion.roles,
                squarePortraitPath = champion.squarePortraitPath,
                showInfo = true,
                isInfo = true,
                title = champion.title,
                shortBio = champion.shortBio
            )
        )

    }
}