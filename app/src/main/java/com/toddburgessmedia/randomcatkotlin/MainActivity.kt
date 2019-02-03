package com.toddburgessmedia.randomcatkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : RandomCatViewModel by viewModel<RandomCatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager?.adapter = adapter

        fab.setOnClickListener {
            viewModel.loadCatPhotos()
        }
    }

}
