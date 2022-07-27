package com.example.kidsstories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsstories.databinding.ItemAnimalBinding

typealias ClickHandler = (position: Int) -> Unit

class AnimalListAdapter(
    private val animalList: ArrayList<Animal>,
    private val listener: ClickHandler = {}
) : RecyclerView.Adapter<AnimalListAdapter.VH>() {

    class VH(
        binding: ItemAnimalBinding,
        listener: ClickHandler /* = (position: Int) -> Unit */
    ) : RecyclerView.ViewHolder(binding.root) {

        val titleItemText = binding.titleTv
        val bgItemImage = binding.bgItem

        init {
            binding.root.setOnClickListener {
                listener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemAnimalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), listener
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.titleItemText.text = animalList[position].title
        holder.bgItemImage.setImageResource(animalList[position].picture)
    }

    override fun getItemCount() = animalList.size
}