package com.example.demojsonplaceholderapi.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(dateString: String): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
            val parsedDate = ZonedDateTime.parse(dateString, formatter)

            // Format the date and time
            val formattedDate =
                DateTimeFormatter.ofPattern("dd-MMMM-yyyy hh:mm:ss a").format(parsedDate)

            // Convert to 12-hour clock time
            LocalDateTime.parse(
                formattedDate,
                DateTimeFormatter.ofPattern("dd-MMMM-yyyy hh:mm:ss a")
            )
                .format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy hh:mm:ss a"))

        } catch (e: DateTimeParseException) {
            "Invalid date format"
        }
    }

    fun openAppFromLink(context: Context, link: String) {
        // Define the package name of the app you want to open
        val packageName = "com.example.app"

        // Create an Intent with ACTION_VIEW and the link
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

        // Set the package name to ensure the intent opens the specific app
        intent.setPackage(packageName)

        // Try to start the activity with the intent
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle any exceptions, such as the app not being installed
            e.printStackTrace()
        }
    }
}