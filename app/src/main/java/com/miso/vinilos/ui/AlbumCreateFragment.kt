package com.miso.vinilos.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.miso.vinilos.R

import com.miso.vinilos.models.Album
import com.miso.vinilos.viewmodels.AlbumCreateViewModel

class AlbumCreateFragment : Fragment() {

    private lateinit var viewModel: AlbumCreateViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.crear_album, container, false)
        val botonGuardar = view.findViewById<Button>(R.id.botonGuardarAlbum)
        botonGuardar.setOnClickListener{
            Toast.makeText(this.context,"y lo creamos",Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, AlbumCreateViewModel.Factory(activity.application)).get(AlbumCreateViewModel::class.java)


    }
}