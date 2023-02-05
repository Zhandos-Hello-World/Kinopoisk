package kz.tinkoff.kinopoisk.presentation.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import kz.tinkoff.kinopoisk.R
import kz.tinkoff.kinopoisk.databinding.FragmentMainBinding
import kz.tinkoff.kinopoisk.presentation.main.FilmsStatePagerAdapter.Companion.VIEW_PAGER_FAVOURITE
import kz.tinkoff.kinopoisk.presentation.main.FilmsStatePagerAdapter.Companion.VIEW_PAGER_TOP

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private var viewPagerAdapter: FilmsStatePagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = FilmsStatePagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    VIEW_PAGER_TOP -> {
                        selectedButtonColor(binding.topBtn)
                        unSelectedButtonColor(binding.favouriteBtn)
                    }
                    VIEW_PAGER_FAVOURITE -> {
                        selectedButtonColor(binding.favouriteBtn)
                        unSelectedButtonColor(binding.topBtn)
                    }
                }
            }
        })

        binding.topBtn.setOnClickListener {
            binding.viewPager.setCurrentItem(VIEW_PAGER_TOP, true)
        }
        binding.favouriteBtn.setOnClickListener {
            binding.viewPager.setCurrentItem(VIEW_PAGER_FAVOURITE, true)
        }
    }


    private fun selectedButtonColor(button: MaterialButton) {
        button.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.blue_sky)
        button.setTextColor(Color.WHITE)
    }

    private fun unSelectedButtonColor(button: MaterialButton) {
        button.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.sky)
        button.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_sky))
    }


}