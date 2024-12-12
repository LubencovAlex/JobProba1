package com.example.jobproba1.apidata

data class VacancyCard(
    val id: String,
    var lookingNumber: Int,
    val title: String,

    val addressTown: String,
    val addressStreet: String,
    val addressHouse: String,

    val company: String,
    val experiencePreviewText: String,
    val experienceText: String,

    val publishedDate: String,
    var isFavorite: Boolean,

    val salaryShort: String,
    val salaryFull: String,

    val schedules: ArrayList<String>,
    val description: String,
    val appliedNumber: Int,
    val responsibilities: String,
    val questions: ArrayList<String>
)
