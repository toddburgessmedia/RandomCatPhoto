package com.toddburgessmedia.randomcatkotlin

import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : androidx.fragment.app.Fragment() {

    private val viewModel: RandomCatViewModel by sharedViewModel<RandomCatViewModel>()

    companion object {

        fun newInstance() : MainActivityFragment {

            val mainActivityFragment = MainActivityFragment()
            return mainActivityFragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.changeNotifier.observe(this, Observer<String> { string ->
            string?.let {
                Picasso.get().load(it).into(cat_photo)
            }
        })

        viewModel.startModelView()
    }


}
