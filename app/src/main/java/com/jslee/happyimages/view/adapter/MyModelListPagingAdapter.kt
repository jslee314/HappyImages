package com.jslee.happyimages.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jslee.happyimages.R
import com.jslee.happyimages.databinding.ViewMyModelHolderBinding
import com.jslee.happyimages.model.Picsum


class MyModelListPagingAdapter(val context : Context):
    PagingDataAdapter<Picsum, MyModelListPagingAdapter.MyModelViewHolder> (
    object: DiffUtil.ItemCallback<Picsum>() {
        override fun areItemsTheSame(oldItem: Picsum, newItem: Picsum): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Picsum, newItem: Picsum): Boolean {
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
        val viewBinding =
            ViewMyModelHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyModelViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyModelViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class MyModelViewHolder(private val viewMyModelHolderBinding: ViewMyModelHolderBinding) :
        RecyclerView.ViewHolder(viewMyModelHolderBinding.root) {

        fun bind(item: Picsum) {

            Glide.with(context)
                .load(item.download_url)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.loading_img)
                .into(viewMyModelHolderBinding.imageView);

            viewMyModelHolderBinding.idxTv.text = item.author

            viewMyModelHolderBinding.imageView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                viewMyModelHolderBinding.imageView.context.startActivity(intent)
            }

        }
    }
}

