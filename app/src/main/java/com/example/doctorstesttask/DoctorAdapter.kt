package com.example.doctorstesttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(private val doctorList:ArrayList<Doctor>):RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DoctorAdapter.MyViewHolder, position: Int) {
        val doctor:Doctor = doctorList[position]
        holder.name.text = doctor.name
        holder.surName.text = doctor.surName
        holder.workExperience.text = doctor.workExperience
        holder.speciality.text = doctor.speciality
        holder.city.text = doctor.city
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

        class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.doctor_name)
        val surName:TextView = itemView.findViewById(R.id.doctor_surname)
        val workExperience: TextView = itemView.findViewById(R.id.doctor_work)
        val speciality:TextView = itemView.findViewById(R.id.doctor_speciality)
        val city :TextView = itemView.findViewById(R.id.doctor_city)
    }
}