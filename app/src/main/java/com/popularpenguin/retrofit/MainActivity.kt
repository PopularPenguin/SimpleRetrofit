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

class MainActivity : AppCompatActivity(), Callback<List<Change>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text.text = "Initializing"

        val udacityUrl = "https://go.udacity.com"
        val baseUrl = "https://git.eclipse.org/r/"
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val gerritApi = retrofit.create(GerritAPI::class.java)
        gerritApi.loadChanges("status:open").enqueue(this)

        val articleAPI = retrofit.create(ArticleService::class.java)
        //articleAPI.articleList().enqueue(this)
    }

    override fun onResponse(call: Call<List<Change>>?, response: Response<List<Change>>?) {
        if (response != null && response.isSuccessful) {
            response.body()
                    ?.forEach { change -> text.append("${change.subject} \n")}
        } else {
            text.text = "Failure to fetch data"
        }
    }

    override fun onFailure(call: Call<List<Change>>?, t: Throwable?) {
        t?.printStackTrace()
    }
}
