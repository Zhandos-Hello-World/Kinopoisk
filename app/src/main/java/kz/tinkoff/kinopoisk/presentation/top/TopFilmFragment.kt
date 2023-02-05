package kz.tinkoff.kinopoisk.presentation.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.tinkoff.kinopoisk.R
import kz.tinkoff.kinopoisk.databinding.FragmentFilmsBinding
import kz.tinkoff.kinopoisk.presentation.adapter.FilmAdapter
import kz.tinkoff.kinopoisk.presentation.utils.hideKeyboardFrom
import kz.tinkoff.kinopoisk.presentation.utils.showKeyboardTo
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopFilmFragment : Fragment(R.layout.fragment_films) {
    private val viewModel: TopFilmViewModel by viewModel()
    private lateinit var binding: FragmentFilmsBinding
    private var adapter: FilmAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilmsBinding.inflate(inflater, container, false)

        adapter = FilmAdapter(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                setLoadingState()
            }
        }
        viewModel.filmItems.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter?.submitList(it)
                binding.data.adapter = adapter
                setData()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                setErrorState()
            }
        }

        binding.repeat.setOnClickListener {
            viewModel.getFilms()
        }

        viewModel.favourites.observe(viewLifecycleOwner) {
            viewModel.setupFavourites()
        }

        viewModel.setNavigationListener {
            findNavController().navigate(it)
        }
        binding.toolbarTitle.setText(R.string.top)
    }

    override fun onResume() {
        super.onResume()
        defaultToolbar()
    }

    override fun onPause() {
        super.onPause()
        defaultToolbar()
    }

    private fun setLoadingState() {
        hideAll()
        binding.loading.visibility = View.VISIBLE
    }

    private fun setData() {
        hideAll()
        binding.data.visibility = View.VISIBLE
    }

    private fun setErrorState() {
        hideAll()
        binding.errorState.visibility = View.VISIBLE
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