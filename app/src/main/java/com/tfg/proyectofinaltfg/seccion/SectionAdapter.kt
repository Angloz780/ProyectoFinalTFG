package com.tfg.proyectofinaltfg.seccion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tfg.proyectofinaltfg.R
import com.tfg.proyectofinaltfg.databinding.SectionListItemBinding
import com.tfg.proyectofinaltfg.pelicula.Movie
import com.tfg.proyectofinaltfg.pelicula.MovieAdapter


class SectionAdapter(val context: Context,val sectionRecycler:  RecyclerView, val onItemClickListener: (Movie) -> Unit) : ListAdapter<Section, SectionAdapter.ViewHolder>(
    DiffCallback
) {

    companion object DiffCallback : DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SectionListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = getItem(position)
        holder.bind(section)
    }

    inner class ViewHolder(private val binding: SectionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) {
            binding.sectionNameText.text = section.name

            binding.sectionNameText.setOnClickListener {
                if (section.expanded) {
                    binding.sectionNameText
                        .setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_baseline_arrow_drop_down_24,
                            0, 0, 0
                        )
                    binding.movieRecycler.visibility = View.GONE
                } else {
                    binding.sectionNameText
                        .setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_baseline_arrow_drop_up_24,
                            0, 0, 0
                        )
                    binding.movieRecycler.visibility = View.VISIBLE
                }

                section.expanded = !section.expanded
            }

            binding.movieRecycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
            val movieAdapter = MovieAdapter(onItemClickListener)
            binding.movieRecycler.adapter = movieAdapter
            movieAdapter.submitList(section.movieList)

            binding.movieRecycler.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    sectionRecycler.suppressLayout(newState != RecyclerView.SCROLL_STATE_IDLE)
                }
            })
        }
    }
}