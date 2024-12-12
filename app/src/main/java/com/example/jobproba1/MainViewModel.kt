package com.example.jobproba1

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jobproba1.apidata.ApiDataAdapter
import com.example.jobproba1.apidata.Offer
import com.example.jobproba1.apidata.VacancyCard
import com.example.jobproba1.apidata.VacancyCardDataAdapter
import org.json.JSONObject

class MainViewModel(application: Application): AndroidViewModel(application), VacancyCardDataAdapter.Listener, ApiDataAdapter.Listener {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    val offerCardData by lazy { MutableLiveData<ArrayList<Offer>>() }
    private var offerCardArrayList = ArrayList<Offer>()

    val vacancyCardData by lazy { MutableLiveData<ArrayList<VacancyCard>>() }
    var vacancyCardArrayList = ArrayList<VacancyCard>()

    val favoriteVacancyCardData by lazy { MutableLiveData<ArrayList<VacancyCard>>() }
    var favoriteVacancyCardArrayList = ArrayList<VacancyCard>()

    private val url = "https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download"

    init{
        getJsonData(context)
        favoriteVacancyCardData.value = favoriteVacancyCardArrayList
    }

    private fun getJsonData(context: Context){
        val apiAdapter = ApiDataAdapter(this)
        val vacancyAdapter = VacancyCardDataAdapter(this)
        val favoriteVacancyAdapter = VacancyCardDataAdapter(this)

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET, url,
            {result ->
                val obj = JSONObject(result)
                val vacancies = obj.getJSONArray("vacancies")
                val offers = obj.getJSONArray("offers")
//            Заполняем список Offers_______________________________________________________________
                for(i in 0 until offers.length()){
                    val itemOffer = offers.getJSONObject(i)
                    val id: String
                    val link: String
                    val title: String
                    val button: String
                    if (itemOffer.has("id")){
                        id = itemOffer.getString("id")
                    } else {
                        id = "null"
                    }
                    if (itemOffer.has("link")){
                        link = itemOffer.getString("link")
                    }else {
                        link = "null"
                    }
                    if (itemOffer.has("title")){
                        title = itemOffer.getString("title")
                    } else {
                        title = "null"
                    }
                    if (itemOffer.has("button")){
                        if (itemOffer.getJSONObject("button").has("text")){
                            button = itemOffer.getJSONObject("button").getString("text")
                        } else {
                            button = "null"
                        }
                    } else {
                        button = "null"
                    }

                    val offerItem = Offer(
                        id = id,
                        link = link,
                        title = title.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        button = button.toByteArray(Charsets.ISO_8859_1).decodeToString()
                    )
                    offerCardArrayList.add(offerItem)
                }
                apiAdapter.submitList(offerCardArrayList)
                offerCardData.value = offerCardArrayList
//            Конец заполнения списка Offers________________________________________________________

//            Заполняем список VacancyCard__________________________________________________________
                for (i in 0 until vacancies.length()){
                    val itemVacancies = vacancies.getJSONObject(i)

                    val id: String
                    val lookingNumber: Int
                    val title: String
                    val addressTown: String
                    val addressStreet: String
                    val addressHouse: String
                    val company: String
                    val experiencePreviewText: String
                    val experienceText: String
                    val publishedDate: String
                    val isFavorite: Boolean
                    val salaryShort: String
                    val salaryFull: String
                    val schedules = arrayListOf("")
                    val description: String
                    val appliedNumber: Int
                    val responsibilities: String
                    val questions = arrayListOf("")
//               ___________________________________________________________________________________
                    if (itemVacancies.has("id")){
                        id = itemVacancies.getString("id")
                    } else{
                        id = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("lookingNumber")){
                        lookingNumber = itemVacancies.getInt("lookingNumber")
                    } else{
                        lookingNumber = 0
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("title")){
                        title = itemVacancies.getString("title")
                    } else{
                        title = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("address")){
                        if (itemVacancies.getJSONObject("address").has("town")){
                            addressTown = itemVacancies.getJSONObject("address").getString("town")
                        }else{
                            addressTown = "null"
                        }

                    } else{
                        addressTown = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("address")){
                        if (itemVacancies.getJSONObject("address").has("street")){
                            addressStreet = itemVacancies.getJSONObject("address").getString("street")
                        }else{
                            addressStreet = "null"
                        }

                    } else{
                        addressStreet = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("address")){
                        if (itemVacancies.getJSONObject("address").has("house")){
                            addressHouse = itemVacancies.getJSONObject("address").getString("house")
                        }else{
                            addressHouse = "null"
                        }

                    } else{
                        addressHouse = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("company")){
                        company = itemVacancies.getString("company")
                    } else{
                        company = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("experience")){
                        if (itemVacancies.getJSONObject("experience").has("previewText")){
                            experiencePreviewText = itemVacancies.getJSONObject("experience").getString("previewText")
                        }else{
                            experiencePreviewText = "null"
                        }

                    } else{
                        experiencePreviewText = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("experience")){
                        if (itemVacancies.getJSONObject("experience").has("text")){
                            experienceText = itemVacancies.getJSONObject("experience").getString("text")
                        }else{
                            experienceText = "null"
                        }

                    } else{
                        experienceText = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("publishedDate")){
                        publishedDate = itemVacancies.getString("publishedDate")
                    } else{
                        publishedDate = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("isFavorite")){
                        isFavorite = itemVacancies.getBoolean("isFavorite")
                    } else{
                        isFavorite = false
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("salary")){
                        if (itemVacancies.getJSONObject("salary").has("short")){
                            salaryShort = itemVacancies.getJSONObject("salary").getString("short")
                        }else{
                            salaryShort = "null"
                        }

                    } else{
                        salaryShort = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("salary")){
                        if (itemVacancies.getJSONObject("salary").has("full")){
                            salaryFull = itemVacancies.getJSONObject("salary").getString("full")
                        }else{
                            salaryFull = "null"
                        }

                    } else{
                        salaryFull = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("schedules")){
                        for (i in 0 until itemVacancies.getJSONArray("schedules").length()){
                            val item = itemVacancies.getJSONArray("schedules").getString(i).toByteArray(Charsets.ISO_8859_1).decodeToString()
                            if (schedules[0]==""){
                                schedules[0] = item
                            } else {
                                schedules.add(item)
                            }
                        }
                    } else {
                        schedules[0] = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("description")){
                        description = itemVacancies.getString("description")
                    } else{
                        description = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("appliedNumber")){
                        appliedNumber = itemVacancies.getInt("appliedNumber")
                    } else{
                        appliedNumber = 0
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("responsibilities")){
                        responsibilities = itemVacancies.getString("responsibilities")
                    } else{
                        responsibilities = "null"
                    }
//               ___________________________________________________________________________________
                    if (itemVacancies.has("questions")){
                        for (i in 0 until itemVacancies.getJSONArray("questions").length()){
                            val item = itemVacancies.getJSONArray("questions").getString(i).toByteArray(Charsets.ISO_8859_1).decodeToString()
                            if(questions[0] == ""){
                                questions[0] = item
                            }else{
                                questions.add(item)
                            }
                        }
                    } else{
                        questions[0] = "null"
                    }
//               ___________________________________________________________________________________
                    val vacancyItem = VacancyCard(
                        id = id,
                        lookingNumber = lookingNumber,
                        title = title.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        addressTown = addressTown.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        addressStreet = addressStreet.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        addressHouse = addressHouse.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        company = company.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        experiencePreviewText = experiencePreviewText.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        experienceText = experienceText.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        publishedDate = publishedDate,
                        isFavorite = isFavorite,
                        salaryShort = salaryShort.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        salaryFull = salaryFull.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        schedules = schedules,
                        description = description.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        appliedNumber = appliedNumber,
                        responsibilities = responsibilities.toByteArray(Charsets.ISO_8859_1).decodeToString(),
                        questions = questions
                    )
                    // Добавляем каждый найденный объект в список вакансий и в список избранное, если отмечен сердечком
                    vacancyCardArrayList.add(vacancyItem)
                    if (vacancyItem.isFavorite){
                        favoriteVacancyCardArrayList.add(vacancyItem)
                    }
                }
                // Добавляем найденные объекты в адаптер и присваеваем заполненные списки в ЛайвДата
                vacancyAdapter.submitList(vacancyCardArrayList)
                vacancyCardData.value = vacancyCardArrayList

                favoriteVacancyAdapter.submitList(favoriteVacancyCardArrayList)
                favoriteVacancyCardData.value = favoriteVacancyCardArrayList
//            Конец заполнения списка VacancyCard___________________________________________________
        },{error ->
                Log.d("MyLog", "Volley ERROR: $error")
        })
        queue.add(stringRequest)
    }

    override fun onClick(item: VacancyCard) {
    }

    override fun onClickLike(item: VacancyCard) {
    }
}