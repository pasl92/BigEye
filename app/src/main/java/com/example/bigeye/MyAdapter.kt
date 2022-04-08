package com.example.bigeye

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigeye.model.Monitor
import com.example.bigeye.model.MonitorListResponse


class MyAdapter(private val data: MonitorListResponse) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(monitor: Monitor){
            val title = view.findViewById<TextView>(R.id.monitorName)
            title.text = monitor.name

            val imageStatus = view.findViewById<ImageView>(R.id.imageMonitorStatus)
            if(monitor.status.status == "Ok"){
                imageStatus.setColorFilter(Color.GREEN)
            } else if(monitor.status.status == "Warning"){
                imageStatus.setColorFilter(Color.YELLOW)
            } else if(monitor.status.status == "Error"){
                imageStatus.setColorFilter(Color.RED)
            } else{
                imageStatus.setColorFilter(Color.GRAY)
            }
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