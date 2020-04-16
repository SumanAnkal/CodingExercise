package com.task.accenturelistviewtask.network

import java.io.IOException

class InternetConnectiveException : IOException() {
    override val message: String get() = "Please Check Your Internet Connection"
}