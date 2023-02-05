package kz.tinkoff.kinopoisk.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import kz.tinkoff.kinopoisk.R
import kz.tinkoff.kinopoisk.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailFragment : Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: FilmDetailViewModel by viewModel()
    private val args: FilmDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.getById(args.id)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                setErrorState()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                setLoadingState()
            }
        }
        viewModel.details.observe(viewLifecycleOwner) { filmDetails ->
            setData()
            binding.filmName.text = filmDetails.title
            binding.filmDesc.text = filmDetails.description
            binding.genres.text = filmDetails.genres
            binding.countries.text = filmDetails.countries

            setupImage(filmDetails.imageUrl)

        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
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
            .into(binding.filmBanner)
    }

    private fun hideAll() {
        binding.data.visibility = View.GONE
        binding.errorState.visibility = View.GONE
        binding.loading.visibility = View.GONE
    }


    private fun setErrorState() {
        hideAll()
        binding.errorState.visibility = View.VISIBLE
    }

    private fun setLoadingState() {
        hideAll()
        binding.loading.visibility = View.VISIBLE
    }


    private fun setData() {
        hideAll()
        binding.data.visibility = View.VISIBLE
    }

}