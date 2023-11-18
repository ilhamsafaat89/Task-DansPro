package com.daurulang.jobinformation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daurulang.jobinformation.R
import com.daurulang.jobinformation.data.Job

class JobListAdapter : RecyclerView.Adapter<JobListAdapter.JobViewHolder>(){

    private val jobList: MutableList<Job> = mutableListOf()

    fun addJobs(jobs: List<Job>){
        jobList.addAll(jobs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_list_item, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    inner class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val jobImageView: ImageView = itemView.findViewById(R.id.iv_fotoJob)
        private val titleTextView: TextView = itemView.findViewById(R.id.tvJudulJob)
        private val locationTextView: TextView = itemView.findViewById(R.id.tvCity)
        private val companyTextView: TextView = itemView.findViewById(R.id.tvCategory)

        fun bind(job: Job){
            Glide.with(itemView.context)
                .load(job.companyLogo)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(jobImageView)

            titleTextView.text = job.title
            locationTextView.text = job.location
            companyTextView.text = job.company

        }
    }
}