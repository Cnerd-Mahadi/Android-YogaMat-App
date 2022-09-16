package com.example.yogamat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.example.yogamat.R
import com.example.yogamat.databinding.YogaListItemBinding
import com.example.yogamat.model.MyYoga
import com.example.yogamat.model.Yoga
import com.example.yogamat.model.YogaRepo
import com.squareup.picasso.Picasso
import java.util.*

class ListMyYogaAdapter(
    private val yogaList: List<MyYoga>,
    private val action: OnClickMyAction,
    private val yogaRepo: YogaRepo
): RecyclerView.Adapter<ListMyYogaAdapter.ListMyYogaViewHolder>() {

    lateinit var binding: YogaListItemBinding

    inner class ListMyYogaViewHolder(binding: YogaListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindJob(yoga: MyYoga, action: OnClickMyAction) {

            binding.yogaTitle.text = yoga.title
            binding.yogaDetails.text = yoga.details
            Picasso.get().load(yogaRepo.getImageUri(yoga)).resize(400, 350).into(binding.yogaListImageview)

            binding.root.setOnClickListener {
                action.onCLick(yoga.id)
            }

            binding.deleteButton.setOnClickListener {
                action.onDelete(yoga)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMyYogaViewHolder {
        binding = YogaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ListMyYogaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListMyYogaViewHolder, position: Int) {
        val yoga = yogaList[position]
        holder.bindJob(yoga, action)
    }

    override fun getItemCount(): Int {
        return yogaList.size
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

interface OnClickMyAction {

    fun onCLick(id: UUID)
    fun onDelete(yoga:MyYoga)
}