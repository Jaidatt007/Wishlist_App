package com.example.wishlistapplication.resources

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun openLinkedIn(context: Context, linkedInProfileId: String) {
    val linkedInAppUri = Uri.parse("linkedin://in/$linkedInProfileId")
    val linkedInWebUri = Uri.parse("https://www.linkedin.com/in/$linkedInProfileId")

    try {
        context.packageManager.getPackageInfo("com.linkedin.android", 0)
        val intent = Intent(Intent.ACTION_VIEW, linkedInAppUri)
        context.startActivity(intent)
    } catch (e: PackageManager.NameNotFoundException) {
        val intent = Intent(Intent.ACTION_VIEW, linkedInWebUri)
        context.startActivity(intent)
    }
}