package com.example.jobproba1.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.jobproba1.MainViewModel
import com.example.jobproba1.R
import com.example.jobproba1.apidata.AllVacancyCardDataAdapter
import com.example.jobproba1.apidata.VacancyCard
import com.example.jobproba1.apidata.VacancyCardDataAdapter
import com.example.jobproba1.databinding.FragmentAllVacancyesBinding

class AllVacanciesFragment : Fragment(), AllVacancyCardDataAdapter.Listener {
    private lateinit var binding: FragmentAllVacancyesBinding
    private val viewModelVacancy: MainViewModel by activityViewModels()

    private val favoriteVacancyAdapter = AllVacancyCardDataAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentAllVacancyesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteCards()
    }

    @SuppressLint("SetTextI18n")
    private fun favoriteCards(){
        viewModelVacancy.vacancyCardData.observe(viewLifecycleOwner){ vac ->
            val vacancyAdapter = AllVacancyCardDataAdapter(this)
            binding.rcAllVacancy.adapter = vacancyAdapter
            vacancyAdapter.submitList(vac)
            binding.textView9.text = "${vac.size} Вакансий"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllVacanciesFragment()
    }

    @SuppressLint("CommitTransaction")
    override fun onClick(item: VacancyCard) {
        val bundle = Bundle()
        bundle.putString("id", item.id)
        bundle.putInt("lookingNumber", item.lookingNumber)
        bundle.putString("title", item.title)
        bundle.putString("addressTown", item.addressTown)
        bundle.putString("addressStreet", item.addressStreet)
        bundle.putString("addressHouse", item.addressHouse)
        bundle.putString("company", item.company)
        bundle.putString("experiencePreviewText", item.experiencePreviewText)
        bundle.putString("experienceText", item.experienceText)
        bundle.putString("publishedDate", item.publishedDate)
        bundle.putBoolean("isFavorite", item.isFavorite)
        bundle.putString("salaryShort", item.salaryShort)
        bundle.putString("salaryFull", item.salaryFull)
        bundle.putStringArrayList("schedules", item.schedules)
        bundle.putString("description", item.description)
        bundle.putInt("appliedNumber", item.appliedNumber)
        bundle.putString("responsibilities", item.responsibilities)
        bundle.putStringArrayList("questions", item.questions)

        val fragment = AccurateFragment()
        fragment.arguments = bundle

        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.frameLayout, fragment).commit()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClickLike(item: VacancyCard) {
        if (item.isFavorite){
            viewModelVacancy.favoriteVacancyCardArrayList.remove(item)
        } else{
            viewModelVacancy.favoriteVacancyCardArrayList.add(item)
        }
        favoriteVacancyAdapter.submitList(viewModelVacancy.favoriteVacancyCardArrayList)
        favoriteVacancyAdapter.notifyDataSetChanged()
        viewModelVacancy.favoriteVacancyCardData.apply { viewModelVacancy.favoriteVacancyCardArrayList }
    }
}