package com.example.wishlistapplication.resources

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}