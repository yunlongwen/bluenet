package com.yurnero.bluetooth

/**
 * Failure occurred with the underlying Bluetooth system.
 */
open class BluetoothException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)

class BluetoothDisabledException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : BluetoothException(message, cause)

open class IOException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message)

open class NotConnectedException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)

class ConnectionRejectedException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)

class NotReadyException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : NotConnectedException(message, cause)

class GattStatusException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)

class ConnectionLostException internal constructor(
    message: String? = null,
    cause: Throwable? = null,
) : NotConnectedException(message, cause)