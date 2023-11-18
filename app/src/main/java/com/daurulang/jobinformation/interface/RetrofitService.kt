package com.daurulang.jobinformation.`interface`


import com.daurulang.jobinformation.data.Job
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("recruitment/positions/")
    fun getJobs(
        @Query("title") title: String?,
        @Query("company") company: String?,
        @Query("location") location: String?,
        @Query("company_logo") companylogo: String?,
    ): Call<List<Job>>

    @GET("positions/{id}")
    fun getJobDetail(@Path("id") id: String): Call<Job>
}