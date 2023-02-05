package kz.tinkoff.kinopoisk.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import kz.tinkoff.kinopoisk.databinding.ItemFilmBinding
import kz.tinkoff.kinopoisk.presentation.base.AdapterListener

class FilmAdapter(private val adapter: AdapterListener) :
    ListAdapter<FilmItem, FilmAdapter.FilmViewHolder>(FilmDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmViewHolder.bind(parent)

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.setup(getItem(position), adapter)
    }

    class FilmViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setup(item: FilmItem, adapter: AdapterListener) {
            setupImage(item.imageUrl)
            binding.filmName.text = item.name
            binding.filmTime.text = item.time
            binding.star.visibility = if (item.favourite) View.VISIBLE else View.INVISIBLE

            viewSetOnTouchListener(item, adapter)
        }

        private fun setupImage(imageUrl: String) {
            val shimmer =
                Shimmer.AlphaHighlightBuilder()
                    .setDuration(1800)
                    .setBaseAlpha(0.7f)
                    .setHighlightAlpha(0.6f)
                    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                    .setAutoStart(true)
                    .build()

            val shimmerDrawable = ShimmerDrawable().apply {
                setShimmer(shimmer)
            }
            Glide.with(binding.root.context)
                .load(imageUrl)
                .placeholder(shimmerDrawable)
                .into(binding.filmImage)
        }

        @SuppressLint("ClickableViewAccessibility")
        private fun viewSetOnTouchListener(
            item: FilmItem,
            adapter: AdapterListener,
        ) {
            binding.root.setOnLongClickListener {
                adapter.onItemPressed(item)
                true
            }

            binding.root.setOnClickListener {
                adapter.onItemClick(item)
            }
        }

        companion object {
            fun bind(parent: ViewGroup): FilmViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ItemFilmBinding = ItemFilmBinding.inflate(inflater, parent, false)
                return FilmViewHolder(binding)
            }
        }
    }
}