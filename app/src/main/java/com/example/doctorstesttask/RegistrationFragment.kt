package com.example.doctorstesttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.doctorstesttask.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    lateinit var navController:NavController
    private lateinit var binding:FragmentRegistrationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding = FragmentRegistrationBinding.bind(view)

        binding.btnPatient.setOnClickListener {
            navController.navigate(R.id.patientRegistration)
        }

        binding.btnDoctor.setOnClickListener {
            navController.navigate(R.id.doctorRegistration)
        }
    }


}