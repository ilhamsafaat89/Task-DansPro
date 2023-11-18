package com.daurulang.jobinformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daurulang.jobinformation.`interface`.RetrofitClient
import com.daurulang.jobinformation.`interface`.RetrofitService
import com.daurulang.jobinformation.adapter.JobListAdapter
import com.daurulang.jobinformation.data.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var jobListAdapter: JobListAdapter
    private var currentPage = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.rvJoblist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        jobListAdapter = JobListAdapter()
        recyclerView.adapter = jobListAdapter

        loadJobs("Software Engineer", "Google", "Mountain View", "google_logo_url")

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


                if (!isLoading && currentPage <10 && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0
                ){
                    loadJobs("Software Engineer", "Google", "Mountain View", "google_logo_url")
                }
            }
        })
    }

    private fun loadJobs(title: String?, company: String?, location: String?, companylogo: String?) {
        if (isLoading) {
            return
        }

        val retrofitService = RetrofitClient.createService(RetrofitService::class.java)

        isLoading = true
        retrofitService.getJobs("Software Engineer", "Google", "Mountain View", "google_logo_url")
            .enqueue(object : Callback<List<Job>> {
                override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        val jobs = response.body()
                        println(jobs)
                        jobs?.let { jobList ->
                            jobListAdapter.addJobs(jobList)
                            currentPage++
                        } ?: showErrorMessage("Response body is null")
                    } else {
                        showErrorMessage("Unsuccessful response: ${response.code()}, ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                    isLoading = false
                    if (t is IOException) {
                        showErrorMessage("Network error: ${t.message}")
                    } else {
                        showErrorMessage("Unexpected error: ${t.message}")
                    }
                }
            })
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}