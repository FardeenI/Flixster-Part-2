package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRecyclerViewAdapter(private val movies: List<Movie>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    // Inflate the item layout from XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null

        val mMovieName: TextView = mView.findViewById(R.id.movie_name)
        val mMovieDescription: TextView = mView.findViewById(R.id.movie_description)
        val mMovieImage: ImageView = mView.findViewById(R.id.movie_image)

        override fun toString(): String {
            return mMovieName.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mMovieName.text = movie.name
        holder.mMovieDescription.text = movie.description

        val imageUrl = movie.image
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500$imageUrl")
            .centerInside()
            .into(holder.mMovieImage)


        // Sets up click listener for this movie item
        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    // Tells the RecyclerView how many items to display
    override fun getItemCount(): Int {
        return movies.size
    }
}