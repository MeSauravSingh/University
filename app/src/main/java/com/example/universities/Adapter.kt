package com.example.universities


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.universities.databinding.RowUniversityBinding

class Adapter(
): ListAdapter<University, Adapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(private val binding: RowUniversityBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(myData: University){
            binding.apply {
                textView.text = myData.name

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallBack: DiffUtil.ItemCallback<University>(){
        override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem == newItem
        }
    }

}