package com.miso.vinilos.ui

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miso.vinilos.R
import com.miso.vinilos.databinding.ArtistaDetailFragmentBinding
import com.miso.vinilos.models.Artista
import com.miso.vinilos.ui.adapters.AlbumsAdapter
import com.miso.vinilos.ui.adapters.ArtistaDetailAdapter
import com.miso.vinilos.viewmodels.ArtistaDetailViewModel

class ArtistaDetailFragment: Fragment() {
    private var _binding: ArtistaDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private var id: String? = ""
    private lateinit var artistaDetailName: TextView
    private lateinit var artistaDetailBirthDay: TextView
    private lateinit var artistaDetailDescripcion: TextView
    private lateinit var artistaDetailImage: ImageView
    private lateinit var viewModel: ArtistaDetailViewModel
    private var viewModelAdapter: ArtistaDetailAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArtistaDetailFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = ArtistaDetailAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        artistaDetailName = binding.artistaDetailName
        artistaDetailBirthDay = binding.artistaDetailBirthDay
        artistaDetailDescripcion = binding.artistaDetailDescripcion
        artistaDetailImage = binding.artistaDetailImage

        //id = arguments?.getString("id")
        arguments?.getString("idArtista").let{
            id =it
        }

        recyclerView = binding.artistaDetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)

       viewModel = ViewModelProvider(this, ArtistaDetailViewModel.Factory(activity.application,this.id)).get(
           ArtistaDetailViewModel::class.java)
        viewModel.artista.observe(viewLifecycleOwner, Observer<Artista> {
            it.apply {
                artistaDetailName.text = this.name
                artistaDetailBirthDay.text = this.birthDate
                artistaDetailDescripcion.text = this.description
                Glide.with(activity).load(this.image).into(artistaDetailImage)
                viewModelAdapter!!.album = this.albums
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