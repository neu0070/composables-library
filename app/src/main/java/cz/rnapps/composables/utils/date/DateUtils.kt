package cz.rnapps.composables.utils.date

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun dateToString(dateToFormat: Date): String {
        val dateFormat = SimpleDateFormat.getDateInstance()
        return dateFormat.format(dateToFormat)
    }

    fun dateToStringNullable(dateToFormat: Date?): String {
        return if(dateToFormat != null) {
            dateToString(dateToFormat)
        } else {
            ""
        }
    }

    fun stringToDate(dateToFormat: String): Date {
        val dateFormat = SimpleDateFormat.getDateInstance()
        return dateFormat.parse(dateToFormat)!!
    }

    fun dateToAPIString(dateToFormat: Date): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH)
        return dateFormat.format(dateToFormat)
    }
}