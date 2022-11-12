package com.miso.vinilos.ui.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilos.databinding.ArtistaItemBinding
import com.miso.vinilos.R
import com.miso.vinilos.models.Artista
import com.bumptech.glide.Glide



class ArtistaAdapter:RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder>() {
    var artista :List<Artista> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val withDataBinding: ArtistaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaViewHolder.LAYOUT,
            parent,
            false)
        return ArtistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artista = artista[position]
        }
        Glide.with(holder.itemView).load(artista[position].image).into(holder.viewDataBinding.artistaImagen)
        //Glide.with(context)

        holder.viewDataBinding.root.setOnClickListener {
            val bundle = bundleOf("idArtista" to artista[position].id.toString())

            holder.viewDataBinding.root.findNavController().navigate(R.id.artistaDetailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return artista.size
    }



    class ArtistaViewHolder(val viewDataBinding: ArtistaItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artista_item
        }
    }
}

