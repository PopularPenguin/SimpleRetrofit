package com.popularpenguin.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Callback<List<Article>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.text = "Initializing..."

        val udacityUrl = "https://go.udacity.com/"

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(udacityUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val articleAPI = retrofit.create(ArticleService::class.java)
        articleAPI.articleList().enqueue(this)
    }

    override fun onResponse(call: Call<List<Article>>?, response: Response<List<Article>>?) {
        if (response != null && response.isSuccessful) {
            tv.text = "" // clear the text field

            response.body()?.forEach { article -> tv.append(formatText(article))}
        } else {
            tv.text = "Failure to fetch data"
        }
    }

    override fun onFailure(call: Call<List<Article>>?, t: Throwable?) {
        t?.printStackTrace()
    }

    private fun formatText(article: Article): String {
        return "${article.id}: ${article.title} by ${article.author}\n\n"
    }
}
