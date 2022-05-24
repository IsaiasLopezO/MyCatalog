package com.example.mycatalog.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycatalog.R
import com.example.mycatalog.databinding.ListProductsBinding
import com.example.mycatalog.model.Product

class adaptador(context: Context, products:List<Product>, onItemListener:OnItemListener): RecyclerView.Adapter<adaptador.ViewHolder>() {

    private val products = products
    private val mOnItemListener = onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptador.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListProductsBinding.inflate(layoutInflater)

        return ViewHolder(binding, mOnItemListener)
    }

    override fun onBindViewHolder(holder: adaptador.ViewHolder, position: Int) {
        holder.bindData(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    interface OnItemListener{
        fun onItemClick(product: Product)
    }

    class ViewHolder(binding:ListProductsBinding, onItemListener:OnItemListener):RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private val ivThumbnail = binding.ivThumbnail
        private val tvTitle = binding.tvTitle
        private val tvProveedor = binding.tvProveedor
        private val tvPrecio = binding.tvPrecio
        private val tvDeliver = binding.tvDeliver
        private val context = binding.root.context
        private val onItemListener = onItemListener
        private lateinit var product: Product

        init{
            binding.root.setOnClickListener(this)
        }

        fun bindData(item: Product){

            tvTitle.text = item.name
            tvProveedor.text = item.provider
            tvPrecio.text = context.getString(R.string.Signo_pesos) + item.price

            if(item.delivery == "0.00"){

                tvDeliver.text = context.getString(R.string.envio_gratis)

            }else{
                tvDeliver.text = context.getString(R.string.Signo_pesos) + item.delivery
            }

            Glide.with(context)
                .load(item.thumbnail_url)
                .into(ivThumbnail)

            product = item
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(product)
        }
    }
}