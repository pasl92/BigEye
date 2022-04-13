package com.example.bigeye

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bigeye.api.ApiClient
import com.example.bigeye.api.SessionManager
import com.example.bigeye.databinding.FragmentMenuBinding
import com.example.bigeye.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuFragment: DialogFragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        apiClient = ApiClient()
        sessionManager = SessionManager(requireActivity())

        fetchUser()
        return binding.root
    }

    private fun fetchUser() {
        apiClient.getApiService()
            .getAccountDetails(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("main", "nie działa")
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseUser = response.body()

                    binding.emailTextView.setText(response.body()?.email)
                    binding.bigeEyeWebButton.setOnClickListener {
                        Toast.makeText(requireActivity(), "go to www.bigeye.pl", Toast.LENGTH_LONG).show()
                        true
                    }
                    binding.logoutAllButton.setOnClickListener {
                        Toast.makeText(requireActivity(), responseUser?.accountId, Toast.LENGTH_LONG).show()
                        true
                    }
                    binding.logoutButton .setOnClickListener {
                        Toast.makeText(requireActivity(), responseUser?.accountId, Toast.LENGTH_LONG).show()
                        true
                    }
                }
            })
    }
}
