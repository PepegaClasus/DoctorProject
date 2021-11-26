package com.example.doctorstesttask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorstesttask.databinding.FragmentProfileBinding
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {
    lateinit var navController: NavController
    private lateinit var binding: FragmentProfileBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var patientArrayList:ArrayList<Patient>
    private lateinit var patientAdapter: PatientAdapter
    private lateinit var db:FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding = FragmentProfileBinding.bind(view)
        recyclerView = binding.patientList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.hasFixedSize()
        patientArrayList = arrayListOf()

        patientAdapter = PatientAdapter(patientArrayList)
        recyclerView.adapter = patientAdapter

        patientArrayList.clear()
        getPatient()



    }

    private fun getPatient(){
        db = FirebaseFirestore.getInstance()
        db.collection("patients")
            .limit(1)
            .addSnapshotListener(object: EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error !=null){
                        Log.e("FireStore error", error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            patientArrayList.clear()
                            patientArrayList.add(dc.document.toObject(Patient:: class.java))
                        }
                    }
                    patientAdapter.notifyDataSetChanged()
                }

            })
    }


}