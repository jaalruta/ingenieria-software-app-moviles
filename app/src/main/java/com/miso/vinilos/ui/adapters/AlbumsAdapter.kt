package com.miso.vinilos.ui.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilos.databinding.AlbumItemBinding
import com.miso.vinilos.R
import com.miso.vinilos.models.Album
import com.bumptech.glide.Glide



class AlbumsAdapter:RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {
    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        Glide.with(holder.itemView).load(albums[position].cover).into(holder.viewDataBinding.imageView)
        //Glide.with(context)

        holder.viewDataBinding.root.setOnClickListener {
            val bundle = bundleOf("idAlbum" to albums[position].id.toString())

            holder.viewDataBinding.root.findNavController().navigate(R.id.albumDetailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }



    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }
}

