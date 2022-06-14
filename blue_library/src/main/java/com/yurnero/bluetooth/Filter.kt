package com.yurnero.bluetooth

import com.benasher44.uuid.Uuid

sealed class Filter {

    /**
     * https://developer.android.com/reference/android/bluetooth/le/ScanFilter.Builder#setManufacturerData(int,%20byte[],%20byte[])
     */
    class ManufacturerData(
        /** A negative [id] is considered an invalid id. */
        val id: Int,

        val data: ByteArray,

        /**
         * For any bit in the mask, set it to 1 if it needs to match the corresponding bit in [data], otherwise set it
         * to 0. [dataMask] must have the same length as [data].
         */
        val dataMask: ByteArray? = null
    ) : Filter()

    class Service constructor(
        val uuid: Uuid
    ) : Filter()
}