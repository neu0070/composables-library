package cz.rnapps.composables.components.custom

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import java.util.*

@Composable
fun CustomDatePicker(
    context: Context,
    onClosed: () -> Unit,
    onConfirmed: (Date) -> Unit
) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            onConfirmed(cal.time)
        }, mYear, mMonth, mDay
    )
    datePickerDialog.setOnDismissListener {
        onClosed()
    }
    datePickerDialog.show()
}