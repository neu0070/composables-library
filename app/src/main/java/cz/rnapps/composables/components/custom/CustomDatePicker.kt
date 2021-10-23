package cz.rnapps.composables.components.custom

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cz.rnapps.composables.utils.date.DateUtils
import java.util.*

@Composable
fun CustomDatePicker(
    context: Context,
    onClosed: () -> Unit,
    onConfirmed: (String) -> Unit
) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            date.value = DateUtils.dateToString(cal.time)
        }, mYear, mMonth, mDay
    )
    datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
        val cal = Calendar.getInstance()
        cal.set(year, month, dayOfMonth)
        onConfirmed(DateUtils.dateToString(cal.time))
    }
    datePickerDialog.setOnDismissListener {
        onClosed()
    }
    datePickerDialog.show()
}