package com.example.bigeye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigeye.model.Monitor
import com.bumptech.glide.Glide
import com.example.bigeye.model.MonitorListResponse


class MyAdapter(private val data: MonitorListResponse) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(monitor: Monitor){
            val title = view.findViewById<TextView>(R.id.monitorName)
            title.text = monitor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.monitor_list, parent, false)
        return MyViewHolder(v)

    }

    override fun getItemCount(): Int {
        return data.monitors.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data.monitors[position])
    }

}