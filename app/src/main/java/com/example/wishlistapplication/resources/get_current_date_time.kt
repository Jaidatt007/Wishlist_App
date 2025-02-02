package com.example.wishlistapplication.resources

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


//Date
fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

fun getDateFromMillis(millis : Long) : String{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date(millis))
}

fun dateInMillisToDate(millis : Long) : String{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date(millis))
}

fun getCurrentDayFromDate(date:String) : String{
    val inputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateObject: Date? = inputDateFormat.parse(date)
    val outputDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dateObject?.let { outputDateFormat.format(it) } ?: "Invalid Date"
}



//Time
fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(Date())
}

fun getCurrentAMPM() : String{
    val dateFormat = SimpleDateFormat("a", Locale.getDefault())
    return dateFormat.format(Date())
}

fun getTimeFromMillis(millis : Long) : String{
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(Date(millis))
}

fun getTimerAfterAddingMinutes(min : Int) : String{
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MINUTE, min)
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

fun getTimerAfterAddingMinutes(millis : Long,min : Int) : String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    calendar.add(Calendar.MINUTE, min)
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(calendar.time)
}
fun getHourInIntFromTime(time : String) : Int{
    val dateFormat = SimpleDateFormat("hh", Locale.getDefault())
    return dateFormat.format(Date(time)).toInt()
}

fun getMinuteInIntFromTime(time : String) : Int{
    val dateFormat = SimpleDateFormat("mm", Locale.getDefault())
    return dateFormat.format(Date(time)).toInt()
}

fun timeInMillisToTime(millis : Long) : String{
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(Date(millis))
}

fun getCurrentHourFromMillis(currentTimeMillis : Long): Int {
    val dateFormat = SimpleDateFormat("HH", Locale.getDefault()) // 24-hour format
    return dateFormat.format(Date(currentTimeMillis)).toInt()
}

fun getCurrentMinuteFromMillis(currentTimeMillis : Long): Int {
    val dateFormat = SimpleDateFormat("mm", Locale.getDefault()) // Minutes
    return dateFormat.format(Date(currentTimeMillis)).toInt()
}

fun getDateAndTimeString(date:String,time: String) : String{
    return "$date $time"
}

fun getMillisecondsDifference(dateTime: String): Long {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getDefault()

    return try {
        val inputDate = dateFormat.parse(dateTime)
        val currentDate = Date()

        if (inputDate != null) {
            inputDate.time - currentDate.time
        } else {
            0L
        }
    } catch (e: Exception) {
        e.printStackTrace()
        0L
    }
}

fun getTimeInFuture(inputTime: String): String {
    val timeFormat = SimpleDateFormat("hh:mm a", Locale.US) // Define input time format
    val currentTime = Calendar.getInstance() // Get current time
    val inputCalendar = Calendar.getInstance() // Create calendar for input time

    try {
        // Parse input time and set it in inputCalendar
        val date = timeFormat.parse(inputTime)
        if (date != null) {
            inputCalendar.time = date
            inputCalendar.set(Calendar.YEAR, currentTime.get(Calendar.YEAR))
            inputCalendar.set(Calendar.MONTH, currentTime.get(Calendar.MONTH))
            inputCalendar.set(Calendar.DAY_OF_MONTH, currentTime.get(Calendar.DAY_OF_MONTH))
        }

        // Check if input time is in the future
        return if (inputCalendar.timeInMillis > currentTime.timeInMillis) {
            timeFormat.format(inputCalendar.time) // Return input time if it's in the future
        } else {
            // If input time is in the past, return current time + 5 minutes
            currentTime.add(Calendar.MINUTE, 5)
            timeFormat.format(currentTime.time)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return "Invalid Time Format"
    }
}

fun extractDate(dateTimeStr: String?): String {
    // Check if the input is null or empty, return a default message if true
    return dateTimeStr?.split(" ")?.getOrNull(0) ?: "Invalid Date"
}

fun extractTime(dateTimeStr: String?): String {
    // Check if the input is null or empty, return a default message if true
    return dateTimeStr?.split(" ")?.let {
        if (it.size >= 3) {
            "${it[1]} ${it[2]}"  // Combine time and AM/PM parts
        } else {
            "Invalid Time"
        }
    } ?: "Invalid Time"
}
