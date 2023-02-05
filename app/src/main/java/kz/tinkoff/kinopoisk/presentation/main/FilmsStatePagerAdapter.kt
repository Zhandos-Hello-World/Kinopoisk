package kz.tinkoff.kinopoisk.presentation.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kz.tinkoff.kinopoisk.presentation.favourites.FavouriteFragment
import kz.tinkoff.kinopoisk.presentation.top.TopFilmFragment


class FilmsStatePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = VIEW_PAGER_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            VIEW_PAGER_TOP -> TopFilmFragment()
            VIEW_PAGER_FAVOURITE -> FavouriteFragment()
            else -> throw IllegalArgumentException("Unknown tab")
        }
    }


    companion object {
        const val VIEW_PAGER_COUNT = 2
        const val VIEW_PAGER_TOP = 0
        const val VIEW_PAGER_FAVOURITE = 1
    }

}