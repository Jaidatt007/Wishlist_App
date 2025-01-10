package com.example.wishlistapplication.resources

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun openGitHub(context: Context, githubUsername: String) {
    val githubWebUri = Uri.parse("https://github.com/$githubUsername")

    // GitHub app does not have a public scheme, fallback directly to browser
    val intent = Intent(Intent.ACTION_VIEW, githubWebUri)
    context.startActivity(intent)
}