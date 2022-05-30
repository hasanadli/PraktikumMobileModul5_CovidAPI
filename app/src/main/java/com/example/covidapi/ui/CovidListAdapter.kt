package com.example.covidapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapi.databinding.ListViewItemBinding
import com.example.covidapi.network.Covid

class CovidListAdapter(val clickListener: GameListener) :
    ListAdapter<Covid, CovidListAdapter.GameViewHolder>(DiffCallback) {

    class GameViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: GameListener, covid: Covid){
            binding.game = covid
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Covid>(){

        override fun areItemsTheSame(oldItem: Covid, newItem: Covid): Boolean {
            return oldItem.provinsi == newItem.provinsi
        }

        override fun areContentsTheSame(oldItem: Covid, newItem: Covid): Boolean {
            return oldItem.kasus == newItem.kasus && oldItem.dirawat == newItem.dirawat
                    && oldItem.sembuh == newItem.sembuh && oldItem.meninggal == newItem.meninggal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int){
        val game = getItem(position)
        holder.bind(clickListener, game)
    }

}

class GameListener(val clickListener: (covid: Covid) -> Unit) {
    fun onClick(covid: Covid) = clickListener(covid)
}