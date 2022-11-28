package com.miso.vinilos.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilos.databinding.ColeccionistaItemBinding
import com.miso.vinilos.R
import com.miso.vinilos.models.Coleccionista
import com.miso.vinilos.ui.ColeccionistaDetailFragment



class ColeccionistaAdapter:RecyclerView.Adapter<ColeccionistaAdapter.ArtistaViewHolder>() {
    var coleccionista :List<Coleccionista> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val withDataBinding: ColeccionistaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaViewHolder.LAYOUT,
            parent,
            false)
        return ArtistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.coleccionista = coleccionista[position]
        }
       // Glide.with(holder.itemView).load(artista[position].image).into(holder.viewDataBinding.artistaImagen)
        //Glide.with(context)

        holder.viewDataBinding.root.setOnClickListener {
            val bundle = bundleOf("idColeccionista" to coleccionista[position].id.toString())

            holder.viewDataBinding.root.findNavController().navigate(R.id.coleccionistaDetailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return coleccionista.size
    }



    class ArtistaViewHolder(val viewDataBinding: ColeccionistaItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.coleccionista_item
        }
    }
}

