package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    companion object {

        fun newInstance() : MainActivityFragment {

            val mainActivityFragment = MainActivityFragment()
            return mainActivityFragment

        }

    }

    lateinit var viewModel: RandomCatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(RandomCatViewModel::class.java)
        } ?: throw Exception ("wrong activity")

        viewModel.changeNotifier.observe(this, Observer<String> { it ->
                Picasso.get().load(it).into(cat_photo)
            }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)

    }

}
