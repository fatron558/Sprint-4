package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Батькович",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "Неизвестен",
    val specialization: String = "Разнорабочий",
    val prevEducation: String? = null
)
