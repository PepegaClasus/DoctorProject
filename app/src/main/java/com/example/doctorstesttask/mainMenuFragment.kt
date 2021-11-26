package com.example.doctorstesttask

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorstesttask.databinding.FragmentMainMenuBinding
import com.google.firebase.firestore.*


class mainMenuFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentMainMenuBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorArrayList:ArrayList<Doctor>
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var db:FirebaseFirestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding = FragmentMainMenuBinding.bind(view)

        recyclerView = binding.doctorList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.hasFixedSize()
        doctorArrayList = arrayListOf()

        doctorAdapter = DoctorAdapter(doctorArrayList)
        recyclerView.adapter = doctorAdapter
        doctorArrayList.clear()
        getDoctors()
        Log.d("!!!", doctorArrayList.toString())
        binding.btnSort.setOnClickListener {
            if (binding.checkBox3.isChecked){

                doctorArrayList.removeIf { it.speciality!! != "Therapist" }
                doctorAdapter.notifyDataSetChanged()

            }
            if (binding.checkBox4.isChecked){

                doctorArrayList.removeIf { it.speciality!! != "Surgeon" }

                doctorAdapter.notifyDataSetChanged()
            }
            if (!binding.checkBox3.isChecked && !binding.checkBox4.isChecked){
                doctorArrayList.clear()
                getDoctors()
                doctorAdapter.notifyDataSetChanged()
            }

            if (binding.checkBox3.isChecked && binding.checkBox4.isChecked){
                doctorArrayList.clear()
                getDoctors()
                doctorAdapter.notifyDataSetChanged()
            }
        }

        binding.btnRegister.setOnClickListener {
            navController.navigate(R.id.registrationFragment)
        }
    }

    private fun getDoctors(){
        db = FirebaseFirestore.getInstance()
        db.collection("doctors")
            .addSnapshotListener(object:EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error !=null){
                        Log.e("FireStore error", error.message.toString())
                        return
                    }

                    for (dc:DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            doctorArrayList.add(dc.document.toObject(Doctor:: class.java))
                        }
                    }
                    doctorAdapter.notifyDataSetChanged()
                }

            })
    }

    override fun onDestroy() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onDestroy()

    }




}