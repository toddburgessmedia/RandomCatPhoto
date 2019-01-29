package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.toddburgessmedia.randomcatkotlin.model.CatPhoto
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_allphotos.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllPhotosFragment : Fragment() {

    lateinit var viewModel: RandomCatViewModel
    lateinit var adapter: AllPhotosAdapter

    companion object {

        fun newInstance() : AllPhotosFragment {

            val allPhotosFragment = AllPhotosFragment()
            return allPhotosFragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_allphotos,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(RandomCatViewModel::class.java)
        } ?: throw Exception ("wrong activity")

        allphotos.layoutManager = LinearLayoutManager(activity)
        viewModel.dbChangeNotifier.observe(this, Observer<List<CatPhoto>> {

                adapter = AllPhotosAdapter(it, context)
                allphotos.adapter = adapter
        })

        viewModel.getAllCatPhotos()
    }

}