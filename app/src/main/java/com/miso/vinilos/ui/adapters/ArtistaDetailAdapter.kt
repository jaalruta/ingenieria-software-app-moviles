package com.miso.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.miso.vinilos.R
import com.miso.vinilos.databinding.ArtistaDetailItemBinding
import com.miso.vinilos.models.Album
import com.miso.vinilos.models.Artista

class ArtistaDetailAdapter: RecyclerView.Adapter<ArtistaDetailAdapter.ArtistaDetailViewHolder>() {
    var album :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaDetailViewHolder {
        val withDataBinding: ArtistaDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaDetailViewHolder.LAYOUT,
            parent,
            false)
        return ArtistaDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = album[position]
        }
        Glide.with(holder.itemView).
        load(album[position].cover).
            apply(
                RequestOptions()
                .placeholder(R.drawable.artistas)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_launcher_background)
            ).
        into(holder.viewDataBinding.coverDetailArtist)
        //Glide.with(context)

        holder.viewDataBinding.root.setOnClickListener {
            val bundle = bundleOf("idAlbum" to album[position].id.toString())

            holder.viewDataBinding.root.findNavController().navigate(R.id.albumDetailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return album.size
    }



    class ArtistaDetailViewHolder(val viewDataBinding: ArtistaDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artista_detail_item
        }
    }
}