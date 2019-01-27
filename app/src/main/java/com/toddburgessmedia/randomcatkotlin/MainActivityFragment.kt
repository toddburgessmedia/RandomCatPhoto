package com.toddburgessmedia.randomcatkotlin

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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

    lateinit var viewModel: RandomCatViewModel

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

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(RandomCatViewModel::class.java)
        } ?: throw Exception ("wrong activity")

        viewModel.changeNotifier.observe(this, Observer<String> {
            Picasso.get().load(it).into(cat_photo)
        })

        viewModel.startModelView()
    }


}
