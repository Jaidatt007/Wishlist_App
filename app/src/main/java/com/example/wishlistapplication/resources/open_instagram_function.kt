package com.example.wishlistapplication.resources

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun openInstagram(context: Context, username: String) {
    val instagramAppUri = Uri.parse("instagram://user?username=$username")
    val instagramWebUri = Uri.parse("https://instagram.com/$username")

    try {
        // Check if Instagram app is installed
        context.packageManager.getPackageInfo("com.instagram.android", 0)
        // Open the Instagram app
        val intent = Intent(Intent.ACTION_VIEW, instagramAppUri)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    } catch (e: PackageManager.NameNotFoundException) {
        // If Instagram app is not installed, open in a web browser
        val intent = Intent(Intent.ACTION_VIEW, instagramWebUri)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}