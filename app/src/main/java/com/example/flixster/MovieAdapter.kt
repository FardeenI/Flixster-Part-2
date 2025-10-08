package com.example.flixster

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        Log.d("AdapterGetItemCount", "Current item count: ${movies.size}")
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val titleTextView = itemView.findViewById<TextView>(R.id.movieTitle)
        private val popularityTextView = itemView.findViewById<TextView>(R.id.moviePopularity)
        private val imageView = itemView.findViewById<ImageView>(R.id.movieImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            Log.d("MovieAdapter", "Binding movie: ${movie.title} ${movie.overview} ${movie.popularity} ${movie.releaseDate}")
            titleTextView.text = movie.title
            popularityTextView.text = movie.popularity?.toString() ?: "N/A"

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .into(imageView)
        }

        override fun onClick(v: View?) {
            val movie = movies[absoluteAdapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}