package com.example.flixster

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"

class DetailActivity : AppCompatActivity() {
    private lateinit var movieTitleTV: TextView
    private lateinit var movieOverviewTV: TextView
    private lateinit var movieReleaseDateTV: TextView
    // private lateinit var moviePopularityTV: TextView
    // private lateinit var movieImageIV: ImageView
    private lateinit var movieBackdropIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieTitleTV = findViewById(R.id.movieTitle)
        movieOverviewTV = findViewById(R.id.movieOverview)
        movieReleaseDateTV = findViewById(R.id.movieReleaseDate)
//        moviePopularityTV = findViewById(R.id.moviePopularity)
//        movieImageIV = findViewById(R.id.movieImage)
        movieBackdropIV = findViewById(R.id.movieBackdrop)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        movieTitleTV.text = movie.title
        movieOverviewTV.text = movie.overview
        movieReleaseDateTV.text = movie.releaseDate
//        moviePopularityTV.text = movie.popularity?.toString() ?: "N/A"

//        Glide.with(this)
//            .load("https://image.tmdb.org/t/p/w500${movie.poster}")
//            .into(movieImageIV)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.backdrop}")
            .into(movieBackdropIV)
    }
}

