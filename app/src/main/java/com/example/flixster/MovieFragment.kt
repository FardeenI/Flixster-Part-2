package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

class MovieFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context

        recyclerView.layoutManager = LinearLayoutManager(context)

        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

        // Using the client, perform the HTTP request

        client[
            "https://api.themoviedb.org/3/movie/now_playing",
            params,
            object :
                JsonHttpResponseHandler()
            {
                /*
                 * The onSuccess function gets called when
                 * HTTP response status is "200 OK"
                 */
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JSON
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    val dataJSON = json.jsonObject.get("results") as JSONArray
                    val moviesRawJSON = dataJSON.toString()

                    // Create a Gson instance to help parse the raw JSON
                    val gson = Gson()

                    // Tell Gson what type we’re expecting (a list of Movie objects)
                    val arrayMoviesType = object : TypeToken<List<Movie>>() {}.type

                    // Convert the raw JSON string into a list of actual Movie data models
                    val models: List<Movie> = gson.fromJson(moviesRawJSON, arrayMoviesType)

                    recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)

                    // Look for this in Logcat:
                    Log.d("MovieFragment", "response successful")
                }


                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("MovieFragment", errorResponse)
                    }
                }
            }]
    }

    override fun onItemClick(item: Movie) {
        Toast.makeText(context, "Movie Name: " + item.name, Toast.LENGTH_LONG).show()
    }
}