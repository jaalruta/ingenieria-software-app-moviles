package com.miso.vinilos.ui

import android.app.AlertDialog
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.miso.vinilos.R
import com.miso.vinilos.databinding.AlbumDetailFragmentBinding
import com.miso.vinilos.models.Album
import com.miso.vinilos.viewmodels.AlbumDetailViewModel
import org.json.JSONObject

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
    private lateinit var albumComentarioLabel: TextView
    private lateinit var albumComentario: EditText
    private lateinit var botonGuardarComentario: Button
    private lateinit var botonComentar: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        botonComentar = view.findViewById<Button>(R.id.botonComentar)
        botonComentar.setOnClickListener{
            botonGuardarComentario.visibility = View.VISIBLE
            albumComentarioLabel.visibility = View.VISIBLE
            albumComentario.visibility = View.VISIBLE
            botonComentar.visibility = View.GONE
            albumDescription.visibility = View.GONE
            //val bundle = bundleOf("idAlbum" to this.id)
            //it.findNavController().navigate(R.id.albumDetailFragment,bundle)
        }

        botonGuardarComentario = view.findViewById<Button>(R.id.botonGuardarComentario)
        botonGuardarComentario.setOnClickListener{
            var comentario = view.findViewById<EditText>(R.id.albumComentario).text.toString();
            var errores = "";
            if (comentario.isEmpty())
            {
                errores+="Debes Ingresar el comentario del album\n";
            }

            if(!errores.isEmpty())
            {
                val builder = AlertDialog.Builder(this.context)

                with(builder)
                {
                    setTitle("Se encontraron los siguientes errores")
                    setMessage(errores)
                    setNegativeButton("Cerrar",null)
                    show()
                }
            }
            else
            {
                viewModel.comentarAlbum(this.id,comentario,{
                    Toast.makeText(this.context,"Se comento el album correctamente"  ,Toast.LENGTH_SHORT).show()
                    botonGuardarComentario.visibility = View.GONE
                    albumComentarioLabel.visibility = View.GONE
                    albumComentario.visibility = View.GONE
                    botonComentar.visibility = View.VISIBLE
                    albumDescription.visibility = View.VISIBLE
                },{
                    var responseError = it.networkResponse
                    var respuesta  = String(responseError.data)
                    val responseObject = JSONObject(respuesta)
                    val message = responseObject.optString("message")
                    val builder = AlertDialog.Builder(this.context)

                    with(builder)
                    {
                        setTitle("Se genero un error al comentar el album")
                        setMessage(message)
                        setNegativeButton("Cerrar",null)
                        show()
                    }
                })
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        albumName = binding.albumName
        albumDate = binding.albumDate
        albumRecordLabel = binding.albumRecordLabel
        albumDescription = binding.albumDescription
        coverImage = binding.coverImage
        albumComentarioLabel = binding.albumComentarioLabel
        albumComentario = binding.albumComentario


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