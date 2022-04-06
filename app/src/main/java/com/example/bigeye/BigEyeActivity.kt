package com.example.bigeye

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.SessionManager
import com.example.bigeye.databinding.ActivityBigeyeBinding
import com.example.bigeye.model.MonitorListResponse
import com.example.bigeye.model.SignUpResponse
import com.example.bigeye.model.UserResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
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
        binding = ActivityBigeyeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        manager = LinearLayoutManager(this)
        fetchPosts()
    }

    private fun fetchPosts() {

        apiClient.getApiService().getMonitorList(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<MonitorListResponse> {
                override fun onFailure(call: Call<MonitorListResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<MonitorListResponse>, response: Response<MonitorListResponse>) {

                    if(response.isSuccessful){
                        Log.d("Main", response.toString())
                        Log.d("Main", response.body().toString())

                        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
                            myAdapter = MyAdapter(response.body()!!)
                            layoutManager = manager
                            adapter = myAdapter
                        }


                    } else {

//                        val parser = JsonParser()
//                        val gson = Gson()
//
//                        var mJson: JsonElement? = parser.parse(response.errorBody()!!.string())
//                        val errorResponse: MonitorListResponse = gson.fromJson(mJson, MonitorListResponse::class.java)
//                        Toast.makeText(this@BigEyeActivity, errorResponse.errorDetails.toString(), Toast.LENGTH_LONG).show()
//                        Log.d("main", errorResponse.errorDetails.toString())

                    }
                }
            })
    }
}