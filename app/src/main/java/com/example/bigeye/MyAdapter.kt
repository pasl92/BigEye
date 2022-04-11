package com.example.bigeye

import android.content.Intent
import android.content.Intent.*
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bigeye.databinding.ActivityBigeyeBinding
import com.example.bigeye.model.Monitor
import com.example.bigeye.model.MonitorListResponse


class MyAdapter(private val data: MonitorListResponse, private val callback: (Monitor) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val viewMonitor: View): RecyclerView.ViewHolder(viewMonitor){

        fun bind(monitor: Monitor){
            val title = viewMonitor.findViewById<TextView>(R.id.monitorName)
            title.text = monitor.name

            val imageStatus = viewMonitor.findViewById<ImageView>(R.id.imageMonitorStatus)
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
        val monitorLayout = LayoutInflater.from(parent.context).inflate(R.layout.monitor_list, parent, false)
        return MyViewHolder(monitorLayout)

    }

    override fun getItemCount(): Int {
        return data.monitors.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data.monitors[position])
        holder.viewMonitor.setOnClickListener {
            callback(data.monitors[position])
        }
    }

}