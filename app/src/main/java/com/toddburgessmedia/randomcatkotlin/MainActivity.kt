package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.toddburgessmedia.randomcatkotlin.model.CatPhotoDB

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel : RandomCatViewModel by lazy {
        ViewModelProviders.of(this).get(RandomCatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,AllPhotosFragment.newInstance())
        fragmentTransaction.commit()

        fab.setOnClickListener {
            viewModel.loadCatPhotos()
        }
    }

}
