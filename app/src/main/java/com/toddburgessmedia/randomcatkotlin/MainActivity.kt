package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mainActivityFragment = MainActivityFragment()

    private val viewModel : RandomCatViewModel by lazy {
        ViewModelProviders.of(this).get(RandomCatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,mainActivityFragment)
        fragmentTransaction.commit()

        fab.setOnClickListener { view ->
            viewModel.loadCatPhotos()
        }
    }

}
