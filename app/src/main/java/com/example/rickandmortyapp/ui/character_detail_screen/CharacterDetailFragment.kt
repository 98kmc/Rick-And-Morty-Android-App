package com.example.rickandmortyapp.ui.character_detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmortyapp.MainActivity
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.CharacterDetailFragmentBinding
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.models.Gender
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    private var _binding: CharacterDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservables()
        viewModel.fetchCharacter(args.characterId)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun configureToolbar() {

        val toolbar = (requireActivity() as MainActivity).binding.mainToolbar
        val character = viewModel.state.value.character ?: return
        val color = when(character.gender) {
            Gender.MALE -> R.color.R_n_M_palette_light_blue
            Gender.FEMALE -> R.color.R_n_M_palette_red
            Gender.GENDERLESS -> R.color.R_n_M_palette_yellow
            Gender.UNKNOWN -> R.color.R_n_M_palette_green
        }


        toolbar.title = character.name
        toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), color))

        //status bar color
        val window: Window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), color)

        //Image Stroke Color
        binding.characterImageview.setStrokeColorResource(color)
    }

    private fun setObservables() {
        viewModel.state.onEach { state ->
            configureUI(character = state.character)
        }.launchIn(lifecycleScope)
    }

    private fun configureUI(character: Character?) {
        if (character == null) return

        binding.characterSpecieTextView.text = character.species
        binding.characterStatusTextView.text = character.status.value
        binding.characterGenderTextView.text = character.gender.value
        binding.characterOriginTextView.text = character.origin.name
        binding.characterFirstSeenTextView.text = kotlin.runCatching { character.episode.first() }.getOrNull() ?: "unknown"

        Glide.with(this)
            .load(character.image.toString())
            .placeholder(R.drawable.placeholder_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.characterImageview)

        configureToolbar()
    }
}