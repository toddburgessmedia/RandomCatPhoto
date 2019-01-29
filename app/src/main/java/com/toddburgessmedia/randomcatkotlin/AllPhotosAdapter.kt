package com.toddburgessmedia.randomcatkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.toddburgessmedia.randomcatkotlin.model.CatPhoto
import kotlinx.android.synthetic.main.rv_allphoto.view.*

class AllPhotosAdapter (val items : List<CatPhoto>?,
                        val context: Context?)
                        : RecyclerView.Adapter<AllPhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_allphoto, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: AllPhotosAdapter.PhotoViewHolder, p1: Int) {
        Picasso.get().load(items?.get(p1)?.file).into(holder.photo)

    }

    class PhotoViewHolder (v : View) : RecyclerView.ViewHolder(v) {

        private var view = v
        val photo = v.rv_image

    }
}