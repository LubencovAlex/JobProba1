package com.example.jobproba1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jobproba1.fragments.AllVacanciesFragment
import com.example.jobproba1.fragments.FavoriteFragment
import com.example.jobproba1.fragments.MessagesFragment
import com.example.jobproba1.fragments.ProfileFragment
import com.example.jobproba1.fragments.ResponsesFragment
import com.example.jobproba1.fragments.SearchFragment
import com.example.jobproba1.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val viewModelVacancy: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateFavoritesCards()

        val fragmentAllVacancies = AllVacanciesFragment()
        fragmentAllVacancies.arguments

        replaceFragment(SearchFragment()) //Запускаем по умолчанию Фрагмент с главным экраном

            // Отслеживаем нажатие по bottomNavigationView и переходим на нужный Фрагмент
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.search -> replaceFragment(SearchFragment())
                R.id.favorite -> {
                    binding.bottomNavigationView.removeBadge(R.id.favorite)
                    replaceFragment(FavoriteFragment())
                }
                R.id.responses -> replaceFragment(ResponsesFragment())
                R.id.messages -> replaceFragment(MessagesFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                else -> {
                }
            }
            true
        }
    }

    // Функция показывает в красном круглишке количество добавленных в понравившиеся
    private fun updateFavoritesCards(){
        viewModelVacancy.favoriteVacancyCardData.observe(this){ vac ->
            if(vac.size>0){
                binding.bottomNavigationView.getOrCreateBadge(R.id.favorite).number = vac.size
            } else{
                binding.bottomNavigationView.removeBadge(R.id.favorite)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}