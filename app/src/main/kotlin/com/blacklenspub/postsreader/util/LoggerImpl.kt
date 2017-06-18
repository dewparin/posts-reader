package com.blacklenspub.postsreader.util

import android.util.Log

class LoggerImpl : Logger {

    override fun logDebug(tag: String, message: String) {
        Log.d(tag, message)
    }
}

