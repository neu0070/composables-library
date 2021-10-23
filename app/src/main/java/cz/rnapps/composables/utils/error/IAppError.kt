package cz.rnapps.composables.utils.error

import androidx.annotation.StringRes
import java.io.Serializable

open class IAppError(
    val throwable: Throwable? = null,
    @StringRes
    val errorMessageResId: Int? = null
): Serializable