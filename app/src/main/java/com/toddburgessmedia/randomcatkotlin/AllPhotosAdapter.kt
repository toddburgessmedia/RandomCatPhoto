package com.toddburgessmedia.randomcatkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.toddburgessmedia.randomcatkotlin.model.CatPhoto
import kotlinx.android.synthetic.main.rv_allphoto.view.*

class AllPhotosAdapter (val items : MutableList<CatPhoto>,
                        val clickListener: (CatPhoto) -> Unit)
                        : RecyclerView.Adapter<AllPhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PhotoViewHolder =
        PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_allphoto, parent, false))


    override fun getItemCount(): Int = items.size

    fun addPhoto(new : List<CatPhoto>) {
//        if (items.last() != new.last()) {
            items.clear()
            items.addAll(new)
            notifyDataSetChanged()
//        }
    }

    override fun onBindViewHolder(holder: AllPhotosAdapter.PhotoViewHolder, p1: Int) {
        Picasso.get().load(items[p1].file).into(holder.photo)
        holder.bind(items[p1],clickListener)


    }

    class PhotoViewHolder (val v : View) : RecyclerView.ViewHolder(v) {

        private var view = v
        val photo = v.rv_image

        fun bind (catPhoto: CatPhoto, clickListener: (CatPhoto) -> Unit) {
            v.setOnClickListener { clickListener(catPhoto) }
        }

    }
}