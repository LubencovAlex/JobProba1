package com.example.jobproba1.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.example.jobproba1.MainViewModel
import com.example.jobproba1.apidata.ApiDataAdapter
import com.example.jobproba1.apidata.Offer
import com.example.jobproba1.apidata.VacancyCard
import com.example.jobproba1.apidata.VacancyCardDataAdapter
import com.example.jobproba1.R
import com.example.jobproba1.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), VacancyCardDataAdapter.Listener, ApiDataAdapter.Listener{

    private val viewModelOffer: MainViewModel by activityViewModels()
    private val viewModelVacancy: MainViewModel by activityViewModels()
    private val viewModelOffer2: MainViewModel by activityViewModels()
    private val viewModelVacancy2: MainViewModel by activityViewModels()

    val apiAdapter = ApiDataAdapter(this)
    val vacancyAdapter = VacancyCardDataAdapter(this)
    val favoriteVacancyAdapter = VacancyCardDataAdapter(this)

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        updateCards()

        // Данный слушатель нажатий переходит на страницу со всем списком полученным из JSONa
        button.setOnClickListener{
            val fragment = AllVacanciesFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCards(){ // Данная функция следит за изменениями в переменных класса MainViewModel и показывает их при их изменениях
        viewModelOffer.offerCardData.observe(viewLifecycleOwner){
            val apiAdapter = ApiDataAdapter(this)
            binding.rcView.adapter =apiAdapter
            if(it.size>0){
                apiAdapter.submitList(it)
            } else{
                binding.rcView.visibility = View.GONE
            }
        }
        viewModelVacancy.vacancyCardData.observe(viewLifecycleOwner){ vac ->
            val vacancyAdapter = VacancyCardDataAdapter(this)
            binding.jobRcList.adapter = vacancyAdapter
            vacancyAdapter.submitList(vac)
        }
        viewModelVacancy.vacancyCardData.observe(viewLifecycleOwner){ allVac ->
            binding.button.text = "Еще ${allVac.size} вакансий"
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    @SuppressLint("CommitTransaction")
    override fun onClick(item: VacancyCard) {
        val bundle = Bundle()  // Создаём переменную класса Bundle и передаём инфу выбранного айтема, чтоб показать отдельно
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
        fragment.arguments = bundle  // Передаём созданный класс как аргументы к фрагменту

        // Теперь открываем новый фрагмент с ранее созданными аргументами
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.frameLayout, fragment).addToBackStack("back").commit()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClickLike(item: VacancyCard) {
        if (!item.isFavorite){
            viewModelVacancy2.favoriteVacancyCardArrayList.remove(item)
        } else{
            viewModelVacancy2.favoriteVacancyCardArrayList.add(item)
        }
        favoriteVacancyAdapter.submitList(viewModelVacancy2.favoriteVacancyCardArrayList)
        favoriteVacancyAdapter.notifyDataSetChanged()
        viewModelVacancy2.favoriteVacancyCardData.apply { viewModelOffer2.favoriteVacancyCardArrayList }
    }

    // Функция для запуска интернетобозревателя, которая делает переход на страничку по ссылке
    override fun onClickOffer(item: Offer) {
        val url = item.link
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}