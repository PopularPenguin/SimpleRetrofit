package com.popularpenguin.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModel()

        tv.text = "Initializing..."
    }

    override fun onResume() {
        super.onResume()

        viewModel.subscribe(tv)
    }

    override fun onDestroy() {
        viewModel.onDestroy()

        super.onDestroy()
    }
}
