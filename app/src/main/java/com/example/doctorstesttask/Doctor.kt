package com.example.doctorstesttask

data class Doctor(
    var name: String? = null,
    var surName: String? = null,
    var workExperience: String? = null, var speciality: String? = null,
    val city: String? = null
) {
}