package com.miso.vinilos.viewmodels.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.miso.vinilos.R
import com.miso.vinilos.databinding.AlbumDetailFragmentBinding
import com.miso.vinilos.models.Album
import com.miso.vinilos.viewmodels.AlbumDetailViewModel

class AlbumDetailFragment: Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private var id: String? = ""
    private lateinit var albumName: TextView
    private lateinit var albumRecordLabel: TextView
    private lateinit var albumDate: TextView
    private lateinit var albumDescription: TextView
    private lateinit var coverImage: ImageView
    private lateinit var viewModel: AlbumDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        albumName = binding.albumName
        albumDate = binding.albumDate
        albumRecordLabel = binding.albumRecordLabel
        albumDescription = binding.albumDescription
        coverImage = binding.coverImage
        //id = arguments?.getString("id")
        arguments?.getString("idAlbum").let{
            id =it
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)

       viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(activity.application,this.id)).get(
           AlbumDetailViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<Album> {
            it.apply {
                albumName.text = this.name
                albumDate.text = this.releaseDate
                albumRecordLabel.text = this.recordLabel
                albumDescription.text = this.description
                Glide.with(activity).load(this.cover).into(coverImage)
            }

        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}