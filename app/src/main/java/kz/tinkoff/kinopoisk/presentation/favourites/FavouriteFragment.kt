package kz.tinkoff.kinopoisk.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.tinkoff.kinopoisk.R
import kz.tinkoff.kinopoisk.data.mapper.FilmFavouriteMapper
import kz.tinkoff.kinopoisk.databinding.FragmentFilmsBinding
import kz.tinkoff.kinopoisk.presentation.adapter.FilmAdapter
import kz.tinkoff.kinopoisk.presentation.utils.hideKeyboardFrom
import kz.tinkoff.kinopoisk.presentation.utils.showKeyboardTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFilmsBinding
    private val viewModel: FavouriteViewModel by viewModel()
    private var adapter: FilmAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        adapter = FilmAdapter(viewModel)
        binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAll()
        binding.data.visibility = View.VISIBLE

        viewModel.favouriteFilms.observe(viewLifecycleOwner) {
            adapter?.submitList(FilmFavouriteMapper().fromFavouritesToItems(it))
            binding.data.adapter = adapter
        }
        binding.toolbarTitle.setText(R.string.favourite)
        defaultToolbar()

        viewModel.setNavigationListener {
            findNavController().navigate(it)
        }
    }

    override fun onPause() {
        super.onPause()
        defaultToolbar()
    }

    private fun hideAll() {
        binding.data.visibility = View.GONE
        binding.errorState.visibility = View.GONE
        binding.loading.visibility = View.GONE
    }

    private fun hideToolBar() {
        binding.back.visibility = View.GONE
        binding.searchText.visibility = View.GONE
        binding.toolbarTitle.visibility = View.GONE
        binding.searchIcon.visibility = View.GONE
    }

    private fun defaultToolbar() {
        hideToolBar()
        binding.toolbarTitle.visibility = View.VISIBLE
        binding.searchIcon.visibility = View.VISIBLE

        binding.searchIcon.setOnClickListener {
            searchToolbar()
        }
        hideKeyboardFrom(requireContext(), binding.searchText)
    }

    private fun searchToolbar() {
        hideToolBar()
        binding.back.visibility = View.VISIBLE
        binding.searchText.visibility = View.VISIBLE

        binding.back.setOnClickListener {
            defaultToolbar()
        }
        showKeyboardTo(requireContext(), binding.searchText)
    }
}