package com.toddburgessmedia.randomcatkotlin

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toddburgessmedia.randomcatkotlin.model.CatPhoto
import kotlinx.android.synthetic.main.fragment_allphotos.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AllPhotosFragment : androidx.fragment.app.Fragment() {

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

        allphotos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        viewModel.dbChangeNotifier.observe(this, Observer<List<CatPhoto>> { list ->

                list?.let {
                    adapter = AllPhotosAdapter(it.toMutableList(),
                        {catphoto : CatPhoto -> itemClicked(catphoto)} )
                    allphotos.adapter = adapter
                    allphotos.addItemDecoration(
                        androidx.recyclerview.widget.DividerItemDecoration(
                            context,
                            androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                        )
                    )


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