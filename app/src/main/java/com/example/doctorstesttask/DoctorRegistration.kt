package com.example.doctorstesttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.doctorstesttask.databinding.FragmentDoctorRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap


class DoctorRegistration : Fragment() {
    lateinit var navController: NavController
    lateinit var binding:FragmentDoctorRegistrationBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_registration, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding = FragmentDoctorRegistrationBinding.bind(view)

        binding.btnDoctorRegistration.setOnClickListener {
            val name = binding.etDoctorName.text.toString()
            val surName = binding.etDoctorSurName.text.toString()
            val workExperience = binding.etWorkExperience.text.toString()
            val speciality = binding.etSpeciality.text.toString()
            val city = binding.etDoctorCity.text.toString()

            saveDoctor(name, surName, workExperience, speciality, city)
            if (name != ""&& surName != "" && workExperience != "" && speciality != "" && city != ""){
                navController.navigate(R.id.mainMenuFragment)
            }else {
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_LONG).show()
            }

        }


    }

    fun saveDoctor(name:String, surName:String, workExperience:String, speciality:String, city:String){
        val db = FirebaseFirestore.getInstance()
        val doctors:MutableMap<String, Any> = HashMap()
        doctors["name"] = name
        doctors["surName"] = surName
        doctors["workExperience"] = workExperience
        doctors["speciality"] = speciality
        doctors["city"] = city

        db.collection("doctors")
            .add(doctors)
            .addOnSuccessListener {
                Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show()
            }

    }


}