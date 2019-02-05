package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toddburgessmedia.randomcatkotlin.model.CatPhoto
import kotlinx.android.synthetic.main.fragment_allphotos.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AllPhotosFragment : Fragment() {

    private val viewModel: RandomCatViewModel by sharedViewModel<RandomCatViewModel>()

    var adapter: AllPhotosAdapter? = null

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

        allphotos.layoutManager = LinearLayoutManager(activity)
        viewModel.dbChangeNotifier.observe(this, Observer<List<CatPhoto>> { list ->

                list?.let {
                    adapter = AllPhotosAdapter(it.toMutableList(),
                        {catphoto : CatPhoto -> itemClicked(catphoto)} )
                    allphotos.adapter = adapter
                    allphotos.addItemDecoration(DividerItemDecoration(context,
                                                DividerItemDecoration.VERTICAL))


                }
        })

        viewModel.catPhotoDAO.getLiveAllCatPhotos().observe(this,
                                Observer<List<CatPhoto>> { list ->

            list?.let {
                adapter?.addPhoto(it)
            }
        })

        viewModel.getAllCatPhotos()
    }

    private fun itemClicked (catPhoto: CatPhoto) {

        viewModel.deletePhoto(catPhoto)
        adapter?.notifyItemChanged(catPhoto.id!!)
    }

}