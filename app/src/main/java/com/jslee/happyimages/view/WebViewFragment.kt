package com.jslee.happyimages.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jslee.happyimages.AppApplication
import com.jslee.happyimages.R
import com.jslee.happyimages.data.Repository
import com.jslee.happyimages.databinding.FragmentWebviewBinding
import com.jslee.happyimages.viewmodel.WebViewViewModel
import com.jslee.happyimages.viewmodel.WebViewViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WebViewFragment : Fragment() {

    private var _binding: FragmentWebviewBinding? = null

    private val webViewViewModel : WebViewViewModel by viewModels {
        WebViewViewModelFactory((Repository()))

    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}