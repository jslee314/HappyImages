package com.jslee.happyimages.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jslee.happyimages.AppApplication
import com.jslee.happyimages.R
import com.jslee.happyimages.data.Repository
import com.jslee.happyimages.databinding.FragmentImagesBinding
import com.jslee.happyimages.viewmodel.ImagesViewModel
import com.jslee.happyimages.viewmodel.ImagesViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null

    private val imagesViewModel : ImagesViewModel by viewModels {
        ImagesViewModelFactory(Repository())
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}