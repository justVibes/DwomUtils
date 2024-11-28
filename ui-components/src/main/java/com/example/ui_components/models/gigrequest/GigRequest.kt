package com.example.ui_components.models.gigrequest

import com.example.ui_components.models.core.user.components.Freelancer
import com.example.ui_components.models.gigrequest.job_info.JobInfo

data class GigRequest(
    /*
    * Being marked as cancelled is useful for letting a non-establishment worker know that a particular job offer has been
    * cancelled (Instead of just deleting the gigRequest once it had been cancelled by the job provider)
    */
    var isCancelled: Boolean = false,
    var cancelMessage: String = "",
    /* This is used to reschedule/change the period that the job was initially set to be completed on to another period */
    var rescheduleJobForm: RescheduleJobForm? = null,
    /* This contains the main details concerning the job */
    var jobInfo: JobInfo = JobInfo(),
    /* This contains information about the user that created the job offer */
    val jobProviderDetails: JobProviderDetails = JobProviderDetails(),
    /* This represents the worker that the job provider selected */
    var selectedWorker: Freelancer? = null,
    /* This represents the workers that are applying for the job */
    var jobApplicants: List<Freelancer> = emptyList(),
)