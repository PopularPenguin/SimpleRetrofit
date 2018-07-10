package com.popularpenguin.retrofit

import android.widget.TextView
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ViewModel {
    companion object {
        const val BASE_URL = "https://go.udacity.com/"
    }

    private val disposables = CompositeDisposable()

    private val networkObservable: Observable<List<Article>>

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val articleAPI = retrofit.create(ArticleService::class.java)
        networkObservable = articleAPI.articleList()
    }

    fun subscribe(view: TextView) {
        fun formatText(article: Article): String {
            return "${article.id}: ${article.title} by ${article.author}: ${article.body.length}\n\n"
        }

        val disposable = networkObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                    view.text = ""
                    list.forEach { view.append(formatText(it)) }
                }

        disposables.add(disposable)
    }

    fun onDestroy() {
        disposables.clear()
    }
}