package com.example.rickandmortyapp.ui.character_detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmortyapp.databinding.CharacterDetailFragmentBinding

class CharacterDetailFragment : Fragment() {


    private var _binding: CharacterDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}