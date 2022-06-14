package com.yurnero.bluetooth

import com.benasher44.uuid.Uuid
import kotlinx.coroutines.flow.Flow

interface Scanner {
    public val advertisements: Flow<Advertisement>
}

fun Scanner(services: List<Uuid>?): Scanner =
    Scanner { filters = services?.map { Filter.Service(it) } }

@JvmName("scannerWithFilters")
fun Scanner(filters: List<Filter>?): Scanner =
    Scanner { this.filters = filters }

fun Scanner(
    builderAction: ScannerBuilder.() -> Unit = {},
): Scanner = ScannerBuilder().apply(builderAction).build()