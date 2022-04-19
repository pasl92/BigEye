package com.example.bigeye

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.SessionManager
import com.example.bigeye.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyAdapter(private val data: MonitorListResponse, private val context: Context, private val callback: (Monitor) -> Unit ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

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

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {

        apiClient = ApiClient()
        sessionManager = SessionManager(context)

        holder.bind(data.monitors[position])
        holder.viewMonitor.setOnClickListener {
            callback(data.monitors[position])
        }

        holder.viewMonitor.setOnLongClickListener{
            apiClient.getApiService()
                .deleteMonitorId(token = "Bearer ${sessionManager.fetchAuthToken()}", id = data.monitors[position].id)
                .enqueue(object : Callback<MonidorDeleteResponse> {
                    override fun onFailure(call: Call<MonidorDeleteResponse>, t: Throwable) {
                        Log.d("main", "nie działa")
                    }

                    override fun onResponse(call: Call<MonidorDeleteResponse>, response: Response<MonidorDeleteResponse>) {
                        if (response.isSuccessful) {
                            (context as BigEyeActivity).finish()
                            val intent = Intent(context, BigEyeActivity::class.java)
                            context.startActivity(intent);

                            Toast.makeText(context, "Monitor " + "\"" +data.monitors[position].name + "\"" + " has been deleted", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(context, data.errorDetails.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                })
            true
        }
    }
}

