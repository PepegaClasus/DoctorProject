package com.example.doctorstesttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PatientAdapter(private val patientList:ArrayList<Patient>): RecyclerView.Adapter<PatientAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.patient_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PatientAdapter.MyViewHolder, position: Int) {
        val patient:Patient = patientList[position]
        holder.name.text = patient.name
        holder.surName.text = patient.surName
        holder.city.text = patient.city
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.patient_name)
        val surName: TextView = itemView.findViewById(R.id.patient_surname)
        val city : TextView = itemView.findViewById(R.id.patient_city)
    }
}