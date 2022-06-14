package com.yurnero.bluetooth

import android.bluetooth.le.ScanSettings
import android.content.Context
import com.benasher44.uuid.Uuid

class ScannerBuilder {

    @Deprecated(
        message = "Replaced by filters property",
        level = DeprecationLevel.ERROR,
    )
    var services: List<Uuid>?
        set(value) {
            filters = value?.map { Filter.Service(it) }
        }
        get() = filters?.filterIsInstance<Filter.Service>()?.map { it.uuid }

    var filters: List<Filter>? = null

//    lateinit var context: Context

    /**
     * Allows for the [Scanner] to be configured via Android's [ScanSettings].
     *
     * This property will be removed in a future version, and will be replaced by a Kable provided DSL for configuring
     * scanning.
     */
    var scanSettings: ScanSettings = ScanSettings.Builder().build()


    internal fun build(): Scanner = AndroidScanner(
        filters = filters,
        scanSettings = scanSettings
    )
}