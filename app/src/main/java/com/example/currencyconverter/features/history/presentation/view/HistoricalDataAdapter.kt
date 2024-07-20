package com.example.currencyconverter.features.history.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.databinding.ItemHistoricalDataBinding
import com.example.currencyconverter.databinding.ItemHistoticalDateBinding
import com.example.currencyconverter.features.history.presentation.uimodel.HistoricalDataByDateUIModel
import com.example.currencyconverter.features.history.presentation.uimodel.HistoricalDataUIModel

class HistoricalDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: MutableList<Any> = mutableListOf()

    fun setData(newData: List<HistoricalDataByDateUIModel>) {
        data.clear()
        newData.forEach { dateItem ->
            data.add(dateItem.date ?: "")
            dateItem.data?.let { data.addAll(it) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_DATE -> {
                val binding = ItemHistoticalDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DateViewHolder(binding)
            }
            ITEM_TYPE_HISTORICAL -> {
                val binding = ItemHistoricalDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HistoricalViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.bind(data[position] as String)
            is HistoricalViewHolder -> holder.bind(data[position] as HistoricalDataUIModel)
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is String -> ITEM_TYPE_DATE
            is HistoricalDataUIModel -> ITEM_TYPE_HISTORICAL
            else -> throw IllegalArgumentException("Unknown data type")
        }
    }

    inner class HistoricalViewHolder(private val binding: ItemHistoricalDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historicalData: HistoricalDataUIModel) {
            binding.historicalData = historicalData
            binding.executePendingBindings()
        }
    }

    inner class DateViewHolder(private val binding: ItemHistoticalDateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: String) {
            binding.date = date
            binding.executePendingBindings()
        }
    }

    companion object {
        private const val ITEM_TYPE_DATE = 1
        private const val ITEM_TYPE_HISTORICAL = 2
    }
}
