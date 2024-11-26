package com.example.ui_components.models.gigrequest.job_info

import com.example.ui_components.models.core.TypeOfWorkerModel
import com.example.ui_components.models.gigrequest.job_info.components.JobPayment
import com.example.ui_components.models.gigrequest.job_info.components.RequestImage
import com.example.ui_components.models.gigrequest.job_info.components.RequestOption
import java.util.Calendar
import java.util.UUID

data class JobInfo(
    val requestId: String = "${UUID.randomUUID()}",
    val creationDate: String = "${Calendar.getInstance().timeInMillis}",
    var dateToDoJob: String = "",
    var timeToDoJob: String = "",
    val payment: JobPayment? = null,
    val requiredTypeOfWorker: TypeOfWorkerModel? = null,
    val selectedRequestOptions: List<RequestOption> = emptyList(),
    var referencePhotos: List<RequestImage> = emptyList(),
)




