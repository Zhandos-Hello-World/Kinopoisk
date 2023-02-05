package kz.tinkoff.kinopoisk.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

class FilmDiffUtil : DiffUtil.ItemCallback<FilmItem>() {

    override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem.name == newItem.name
    }
}