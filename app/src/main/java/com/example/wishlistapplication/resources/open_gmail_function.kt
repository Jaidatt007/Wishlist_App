package com.example.wishlistapplication.resources

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun openGmail(context: Context, email: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain" // MIME type for text content
        setPackage("com.google.android.gm") // Specifically target the Gmail app
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email)) // Recipient
        putExtra(Intent.EXTRA_SUBJECT, subject) // Subject
        putExtra(Intent.EXTRA_TEXT, body) // Email body
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        // Handle case where Gmail is not installed
        val fallbackIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("mailto:$email?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}")
        }
        context.startActivity(fallbackIntent)
    }
}