package com.jslee.happyimages.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jslee.happyimages.view.adapter.MyModelListPagingAdapter
import com.jslee.happyimages.data.Repository
import com.jslee.happyimages.databinding.FragmentImagesBinding
import com.jslee.happyimages.viewmodel.ImagesViewModel
import com.jslee.happyimages.viewmodel.ImagesViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null

    private val imagesViewModel : ImagesViewModel by viewModels {
        ImagesViewModelFactory(Repository())
    }
    private val myModelListPagingAdapter: MyModelListPagingAdapter
            by lazy { MyModelListPagingAdapter(requireActivity().applicationContext) }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImagesBinding.inflate(inflater, container, false)

        imagesViewModel.author.observe(viewLifecycleOwner) { author ->
            lifecycleScope.launch {
                binding.textviewFirst.text = author.toString()
            }
        }

        setUpRecyclerView()
        setUpObserver()

        return binding.root
    }

    private fun setUpRecyclerView() {

        binding.recyclerView.adapter = myModelListPagingAdapter


        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (myModelListPagingAdapter.getItemViewType(position)) {
                            myModelListPagingAdapter.contentsType -> {
                                3
                            }
                            myModelListPagingAdapter.loadStateType -> {
                                1
                            }
                            else -> {
                                0
                            }
                        }
                    }

                }
            }
            adapter = myModelListPagingAdapter
        }
    }

    private fun setUpObserver() {
        imagesViewModel.imageList.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                myModelListPagingAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}