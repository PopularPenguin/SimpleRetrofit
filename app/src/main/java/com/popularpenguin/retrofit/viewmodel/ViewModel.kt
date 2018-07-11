package com.popularpenguin.retrofit.viewmodel

import android.widget.TextView
import com.popularpenguin.retrofit.R
import com.popularpenguin.retrofit.model.Article
import com.popularpenguin.retrofit.retrofit.ArticleService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class ViewModel @Inject constructor(retrofit: Retrofit) {
    private val disposables = CompositeDisposable()

    private val networkObservable: Observable<List<Article>>

    init {
        val articleAPI = retrofit.create(ArticleService::class.java)
        networkObservable = articleAPI.articleList()
    }

    fun subscribe(view: TextView) {
        fun formatText(article: Article): String {
            return "${article.id}: ${article.title} by ${article.author}: ${article.body.length}\n\n"
        }

        val disposable = networkObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { ArrayList<Article>() }
                .subscribe { list ->
                    view.text = ""

                    if (list.isEmpty()) {
                        view.setText(R.string.tv_error)
                    } else {
                        list.forEach { view.append(formatText(it)) }
                    }
                }

        disposables.add(disposable)
    }

    fun onDestroy() {
        disposables.clear()
    }
}