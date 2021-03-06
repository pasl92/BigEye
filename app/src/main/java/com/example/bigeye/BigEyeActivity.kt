package com.example.bigeye

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.SessionManager
import com.example.bigeye.databinding.ActivityBigeyeBinding
import com.example.bigeye.model.MonitorIdResponse
import com.example.bigeye.model.MonitorListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BigEyeActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var binding: ActivityBigeyeBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        manager = LinearLayoutManager(this)
        fetchPosts()

        binding = ActivityBigeyeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMenu.setOnClickListener {
            val menuFragment = MenuFragment()
            menuFragment.show(supportFragmentManager, "menuFragment")
        }

        binding.buttonMenu.setOnLongClickListener{
            Log.d("main", "press działa")
            true
        }

        binding.buttonAdd.setOnClickListener{
            val addMonitorActivity = Intent(applicationContext, AddMonitorActivity::class.java)
            startActivity(addMonitorActivity)
        }

        binding.buttonAdd.setOnLongClickListener{
            finish()
            overridePendingTransition(0, 0);
            startActivity(getIntent())
            true
        }


    }

    private fun fetchPosts() {

        apiClient.getApiService()
            .getMonitorList(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<MonitorListResponse> {
                override fun onFailure(call: Call<MonitorListResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MonitorListResponse>,
                    response: Response<MonitorListResponse>
                ) {

                    if (response.isSuccessful) {
                        val monitorList = response
                        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                            myAdapter = MyAdapter(response.body()!!, context = this.context){monitor ->
                                binding.mainTitle.setText(monitor.name)
                                binding.monitorDescriptioneTextView.setText(monitor.description)
                                binding.monitorTimeOutTextView.setText("timeout: " + monitor.status.averageResponseTime)
                                binding.monitorStatusTextView.setText("status: " + monitor.status.status)
                            }
                            layoutManager = manager
                            adapter = myAdapter
                            getDataMonitorById(monitorList)
                        }
                    }
                }
            })
    }

    private fun getDataMonitorById(monitorList:Response<MonitorListResponse>) {

        if (monitorList.body()?.monitors?.isEmpty() == false) {

            apiClient.getApiService().getMonitorId(
                token = "Bearer ${sessionManager.fetchAuthToken()}",
                id = monitorList.body()?.monitors?.get(0)?.id.toString()
            ).enqueue(object : Callback<MonitorIdResponse> {
                    override fun onFailure(call: Call<MonitorIdResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<MonitorIdResponse>,
                        response: Response<MonitorIdResponse>
                    ) {

                        if (response.isSuccessful) {
                            Log.d("Main", response.toString())
                            Log.d("Main", response.body().toString())

                        }
                        val mainNaneMonitor: String = response.body()?.name.toString()
                        val detailsFullAdressMonitor: String =
                            response.body()?.configuration?.address.toString()
                        val detailsAverageResponse: String =
                            response.body()?.status?.averageResponseTime.toString()
                        val detailsStatus: String = response.body()?.status?.status.toString()

                        if (mainNaneMonitor != null) {
                            binding.mainTitle.setText(mainNaneMonitor)
                            binding.monitorDescriptioneTextView.setText(detailsFullAdressMonitor)
                            binding.monitorTimeOutTextView.setText("timeout: " + detailsAverageResponse)
                            binding.monitorStatusTextView.setText("status: " + detailsStatus)
                            Log.d("main", mainNaneMonitor)
                        } else {
                            //test
                        }
                    }
                })
        }else{
            binding.mainTitle.setText("Set monitor")
            binding.monitorDescriptioneTextView.setText("name: -")
            binding.monitorTimeOutTextView.setText("timeout: -" )
            binding.monitorStatusTextView.setText("status: - ")
        }
    }
}