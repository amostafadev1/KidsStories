package com.example.kidsstories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsstories.databinding.ItemAnimalBinding

class AnimalListAdapter(
    private val animalList: ArrayList<Animal>,
    private val listener: AnimalItemCL
) :
    RecyclerView.Adapter<AnimalListAdapter.VH>() {
    inner class VH(binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val titleItemText = binding.titleTv
        val bgItemImage = binding.bgItem

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(v, adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.titleItemText.text = animalList[position].title
        holder.bgItemImage.setImageResource(animalList[position].picture)
    }

    override fun getItemCount() = animalList.size

    fun interface AnimalItemCL {
        fun onClick(view: View?, position: Int)
    }
}