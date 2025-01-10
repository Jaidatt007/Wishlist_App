package com.example.wishlistapplication.resources

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun openWhatsAppChat(context: Context, phoneNumber: String, message: String = "") {
    val whatsappUri = Uri.parse("https://wa.me/$phoneNumber?text=${Uri.encode(message)}")
    val whatsappAppUri = Uri.parse("smsto:$phoneNumber")

    try {
        // Check if WhatsApp is installed
        context.packageManager.getPackageInfo("com.whatsapp", 0)
        // Open WhatsApp app
        val intent = Intent(Intent.ACTION_SENDTO, whatsappAppUri)
        intent.setPackage("com.whatsapp")
        intent.putExtra("sms_body", message) // Optional for the app
        context.startActivity(intent)
    } catch (e: PackageManager.NameNotFoundException) {
        // If WhatsApp app is not installed, open WhatsApp Web
        val intent = Intent(Intent.ACTION_VIEW, whatsappUri)
        context.startActivity(intent)
    }
}