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
import com.miso.vinilos.databinding.ColeccionistaDetailFragmentBinding
import com.miso.vinilos.models.Coleccionista
import com.miso.vinilos.ui.adapters.AlbumsAdapter
import com.miso.vinilos.viewmodels.ColeccionistaDetailViewModel

class ColeccionistaDetailFragment: Fragment() {
    private var _binding: ColeccionistaDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private var id: String? = ""
    private lateinit var coleccionistaDetailName: TextView
    private lateinit var viewModel: ColeccionistaDetailViewModel
    private var viewModelAdapter: AlbumsAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ColeccionistaDetailFragmentBinding.inflate(inflater, container, false)
        viewModelAdapter = AlbumsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coleccionistaDetailName = binding.collectorDetailName


        //id = arguments?.getString("id")
        arguments?.getString("idColeccionista").let{
            id =it
        }

        recyclerView = binding.collectorDetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)

       viewModel = ViewModelProvider(this, ColeccionistaDetailViewModel.Factory(activity.application,this.id)).get(
           ColeccionistaDetailViewModel::class.java)
        viewModel.coleccionista.observe(viewLifecycleOwner, Observer<Coleccionista> {
            it.apply {
                coleccionistaDetailName.text = this.name
                viewModelAdapter!!.albums = this.albums
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