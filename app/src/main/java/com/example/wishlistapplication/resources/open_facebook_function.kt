package com.example.wishlistapplication.resources

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun openFacebook(context: Context, facebookPageId: String) {
    val facebookAppUri = Uri.parse("fb://page/$facebookPageId")
    val facebookWebUri = Uri.parse("https://www.facebook.com/$facebookPageId")

    try {
        context.packageManager.getPackageInfo("com.facebook.katana", 0)
        val intent = Intent(Intent.ACTION_VIEW, facebookAppUri)
        context.startActivity(intent)
    } catch (e: PackageManager.NameNotFoundException) {
        val intent = Intent(Intent.ACTION_VIEW, facebookWebUri)
        context.startActivity(intent)
    }
}