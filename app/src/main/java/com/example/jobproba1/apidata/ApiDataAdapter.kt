package com.example.jobproba1.apidata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobproba1.R
import com.example.jobproba1.databinding.CardJobSearchBinding

class ApiDataAdapter(private val listener: Listener): ListAdapter<Offer, ApiDataAdapter.Holder>(Comparator()) {
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = CardJobSearchBinding.bind(view)
        fun bind(item: Offer, listener: Listener) = with(binding){
            titleText.setOnClickListener {
                listener.onClickOffer(item)
            }
            if (item.id =="near_vacancies"){
                imageView.setImageResource(R.drawable.blue_background)
                imageView.foreground = ContextCompat.getDrawable(imageView.context,
                    R.drawable.location
                )
            }
            if(item.id == "level_up_resume"){
                textLink.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.green_background)
                imageView.foreground = ContextCompat.getDrawable(imageView.context,
                    R.drawable.green_star
                )
            }else{
                textLink.visibility = View.GONE
            }
            if (item.id =="temporary_job"){
                imageView.setImageResource(R.drawable.blue_background)
                imageView.foreground = ContextCompat.getDrawable(imageView.context,
                    R.drawable.job_and_for_time
                )
            }
            if (item.id =="null"){
                imageView.setImageResource(R.drawable.blue_background)
                imageView.foreground = ContextCompat.getDrawable(imageView.context,
                    R.drawable.sharp_article_24
                )
            }
            titleText.text  = item.title
            imageView.foreground

        }
    }

    class Comparator: DiffUtil.ItemCallback<Offer>(){
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_job_search, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener{
        fun onClickOffer(item: Offer){}
    }
}