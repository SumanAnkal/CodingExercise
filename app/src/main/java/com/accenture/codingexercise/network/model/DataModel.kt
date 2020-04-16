package com.task.accenturelistviewtask.network.model

data class DataModel(
    val rows: List<Row>,
    val title: String
)

data class Row(
    val description: String,
    val imageHref: String,
    val title: String
)