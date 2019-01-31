package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.toddburgessmedia.randomcatkotlin.model.CatPhotoDB

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel : RandomCatViewModel by lazy {
        ViewModelProviders.of(this).get(RandomCatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager?.adapter = adapter

        fab.setOnClickListener {
            viewModel.loadCatPhotos()
        }
    }

}
