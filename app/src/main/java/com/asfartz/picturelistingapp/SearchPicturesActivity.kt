package com.asfartz.picturelistingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject

class SearchPicturesActivity : AppCompatActivity() {

    private val TAG = "QuerryPicturesActivity"

    lateinit var mRecyclerView: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var mAdapter: PictureAdapter
    lateinit var mPicturesUrlList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_pictures)
        initViews()

        val baseUrl = "https://api.unsplash.com"
        val randomPhotoUrl = "$baseUrl/photos/random?client_id=${MyApplication.getUnsplashAPIKey()}"
        val searchPhotosUrl = "$baseUrl/search/photos?${
            makeSearchQuery(
                searchKey = "FIRE",
                perPage = 10,
                orientation = "portrait",
                page = 1
            )
        }&client_id=${MyApplication.getUnsplashAPIKey()}"

        // Instantiate the RequestQueue.
        val queue = SingletonRequestQueue.getInstance(applicationContext);
        val stringRequest = object : StringRequest(
            Request.Method.GET, searchPhotosUrl,
            Response.Listener { response ->
                Log.d(TAG, "resp = $response")
                try {
                    val jsonObjectResponse = JSONObject(response)
                    val jsonArrayResults = jsonObjectResponse.getJSONArray("results");
                    for (i in 0 until jsonArrayResults.length()) {
                        val jsonResult = jsonArrayResults.getJSONObject(i)
                        val jsonUrlsObject = jsonResult.getJSONObject("urls")
                        Log.d(TAG, "jsonUrlsArray = $jsonUrlsObject")
                        mPicturesUrlList.add(jsonUrlsObject.getString("regular"))
                    }
                    mRecyclerView.adapter = PictureAdapter(mPicturesUrlList)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            },
            Response.ErrorListener { error ->
                Log.d(TAG, "error = $error")
            }) {}

        queue.add(stringRequest)

    }

    fun initViews() {
        mPicturesUrlList = ArrayList()

        mLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        mAdapter = PictureAdapter(listOf("a", "b", "c", "d", "e", "f", "g", "h", "i"))

        mRecyclerView = findViewById(R.id.my_reycler_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    private fun makeSearchQuery(
        searchKey: String,
        perPage: Int,
        orientation: String,
        page: Int
    ): String {
        return "query=$searchKey&per_page=$perPage&orientation=$orientation&page=$page"
    }

}