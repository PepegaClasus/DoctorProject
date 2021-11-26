package com.example.doctorstesttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.doctorstesttask.databinding.FragmentPatientRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class PatientRegistration : Fragment() {
    lateinit var navController: NavController
    lateinit var binding:FragmentPatientRegistrationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_registration, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding = FragmentPatientRegistrationBinding.bind(view)



        binding.btnRegistration.setOnClickListener {
            val name = binding.etPatientName.text.toString()
            val secName = binding.etPatientSecondName.text.toString()
            val city = binding.etPatientCity.text.toString()
            savePatient(name, secName, city)
            navController.navigate(R.id.mainMenuFragment)

        }

    }

    fun savePatient(name:String, secName:String, city:String){
        val db = FirebaseFirestore.getInstance()
        val patients:MutableMap<String, Any> = HashMap()
        patients["name"] = name
        patients["surName"] = secName
        patients["city"] = city

        db.collection("patients")
            .add(patients)
            .addOnSuccessListener {
                Toast.makeText(context, "регистрация прошла успешно",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "Ошибка",Toast.LENGTH_LONG).show()
            }




    }

    fun deletePatients(){
        val db = FirebaseFirestore.getInstance()
        db.collection("patients").document("")
            .delete()
    }




}

