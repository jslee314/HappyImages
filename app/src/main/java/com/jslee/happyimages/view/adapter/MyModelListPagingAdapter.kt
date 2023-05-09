package com.jslee.happyimages.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jslee.happyimages.databinding.ViewMyModelHolderBinding
import com.jslee.happyimages.model.Picsum

class MyModelListPagingAdapter: PagingDataAdapter<Picsum, MyModelListPagingAdapter.MyModelViewHolder>(
    object: DiffUtil.ItemCallback<Picsum>() {
        override fun areItemsTheSame(oldItem: Picsum, newItem: Picsum): Boolean {
            // 같은 객체인지 check
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Picsum, newItem: Picsum): Boolean {
            // 같은 내용물인지 check
            return oldItem.id == newItem.id
        }
    }
) {

    val contentsType = 1
    val loadStateType = 2

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            contentsType
        } else {
            loadStateType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyModelViewHolder {
        val viewBinding = ViewMyModelHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyModelViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyModelViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class MyModelViewHolder(private val viewMyModelHolderBinding: ViewMyModelHolderBinding): RecyclerView.ViewHolder(viewMyModelHolderBinding.root) {
        fun bind(item: Picsum) {
            viewMyModelHolderBinding.emojiTv.text = item.author
            viewMyModelHolderBinding.idxTv.text = item.url
        }

    }

}
