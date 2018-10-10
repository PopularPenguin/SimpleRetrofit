package com.popularpenguin.retrofit.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.popularpenguin.retrofit.R
import com.popularpenguin.retrofit.application.RetrofitApplication
import com.popularpenguin.retrofit.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as RetrofitApplication).retrofitComponent.inject(this)

        tv.setText(R.string.tv_init)
    }

    override fun onResume() {
        super.onResume()

        val disposable = viewModel.fetchNetworkObservable()
                .subscribe(
                        { list ->
                            tv.text = ""

                            if (list.isEmpty()) {
                                tv.setText(R.string.tv_error)
                            } else {
                                list.forEach { tv.append(viewModel.formatText(it)) }
                            }
                        }, { _ -> tv.setText(R.string.tv_error) }
                )

        viewModel.addDisposable(disposable)
    }

    override fun onDestroy() {
        viewModel.onDestroy()

        super.onDestroy()
    }
}
