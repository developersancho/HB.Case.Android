package com.developersancho.hb.features.search

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.BindingViewHolder
import com.developersancho.framework.extensions.cast
import com.developersancho.framework.extensions.toBinding
import com.developersancho.framework.extensions.toReleaseDate
import com.developersancho.hb.R
import com.developersancho.hb.databinding.ItemSearchBinding

class SearchAdapter : PagingDataAdapter<SearchItemDto, RecyclerView.ViewHolder>(SearchDiffUtil) {
    companion object SearchDiffUtil : DiffUtil.ItemCallback<SearchItemDto>() {
        override fun areItemsTheSame(oldItem: SearchItemDto, newItem: SearchItemDto) =
            oldItem.trackId == newItem.trackId && oldItem.collectionId == newItem.collectionId
                    && oldItem.trackName == newItem.trackName && oldItem.collectionName == newItem.collectionName
                    && oldItem.artistName == newItem.artistName && oldItem.artworkUrl100 == newItem.artworkUrl100

        override fun areContentsTheSame(oldItem: SearchItemDto, newItem: SearchItemDto) =
            oldItem == newItem
    }

    var onClickItem: ((SearchItemDto) -> Unit)? = null

    inner class SearchViewHolder(binding: ItemSearchBinding) :
        BindingViewHolder<ItemSearchBinding>(binding) {

        @SuppressLint("SetTextI18n")
        fun bind(item: SearchItemDto) {
            binding.ivArtNetwork.load(item.artworkUrl100.toString()) {
                error(R.drawable.ic_error_image)
            }
            binding.tvCollectionName.text = item.collectionName
            binding.tvCollectionPrice.text = "${item.collectionPrice}-${item.currency}"
            binding.tvReleaseDate.text = item.releaseDate?.toReleaseDate()
            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val searchItem = getItem(position)
        searchItem?.let { holder.cast<SearchViewHolder>().bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(parent.toBinding())
    }
}