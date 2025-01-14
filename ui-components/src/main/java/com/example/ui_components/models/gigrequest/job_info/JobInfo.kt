package com.example.ui_components.models.gigrequest.job_info

import com.example.ui_components.models.gigrequest.job_info.components.JobOption
import com.example.ui_components.models.gigrequest.job_info.components.JobPayment
import com.example.ui_components.models.gigrequest.job_info.components.RequestImage
import com.example.ui_components.models.user.components.freelancer_info.components.metadata.FreelancerMetadata
import java.util.Calendar
import java.util.UUID

data class JobInfo(
    val jobUid: String = "${UUID.randomUUID()}",
    val creationDate: String = "${Calendar.getInstance().timeInMillis}",
    var dateToDoJob: String = "",
    var timeToDoJob: String = "",
    val payment: JobPayment? = null,
    val requiredTypeOfWorker: FreelancerMetadata? = null,
    val selectedJobOptions: List<JobOption> = emptyList(),
    var referencePhotos: List<RequestImage> = emptyList(),
)




