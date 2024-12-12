package com.example.jobproba1.apidata

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobproba1.R
import com.example.jobproba1.databinding.CardVacancyesForYouBinding

class AllVacancyCardDataAdapter(private val listener: Listener): ListAdapter<VacancyCard, AllVacancyCardDataAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = CardVacancyesForYouBinding.bind(view)
        @SuppressLint("SetTextI18n")
        fun bind(item: VacancyCard, listener: Listener) = with(binding){
            itemView.setOnClickListener {
                listener.onClick(item)
            }
            if (item.isFavorite){
                imageButton2.setImageResource(R.drawable.heart__state_active)
            } else{
                imageButton2.setImageResource(R.drawable.favorite)
            }
            when(item.lookingNumber){
                1 -> textView3.text = "Сейчас просматривает ${item.lookingNumber} человек"
                in 2..4 -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человека"
                in 22..24 -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человека"
                in 32..34 -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человека"
                in 42..44 -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человека"
                in 52..54 -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человека"
                in 62..64 -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человека"
                else -> textView3.text = "Сейчас просматривают ${item.lookingNumber} человек"
            }

            textView4.text = item.title
            textView5.text = item.addressTown
            textView6.text = item.company
            textView7.text = item.experiencePreviewText
            textView8.text = "Опубликовано ${item.publishedDate}"
        }
    }

    class Comparator: DiffUtil.ItemCallback<VacancyCard>(){
        override fun areItemsTheSame(oldItem: VacancyCard, newItem: VacancyCard): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VacancyCard, newItem: VacancyCard): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_vacancyes_for_you, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener{
        fun onClick(item: VacancyCard)
    }
}