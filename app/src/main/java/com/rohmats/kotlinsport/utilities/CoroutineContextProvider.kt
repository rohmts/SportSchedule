package com.rohmats.kotlinsport.utilities

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Rohmats on 21/10/18.
 */

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}