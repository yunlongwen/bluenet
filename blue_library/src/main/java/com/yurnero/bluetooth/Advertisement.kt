package com.yurnero.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.BOND_BONDED
import android.bluetooth.BluetoothDevice.BOND_BONDING
import android.bluetooth.BluetoothDevice.BOND_NONE
import android.bluetooth.le.ScanResult
import android.os.ParcelUuid
import com.benasher44.uuid.Uuid

enum class BondState {
    None,
    Bonding,
    Bonded,
}

@SuppressLint("MissingPermission", "NewApi")
class Advertisement(
    private val scanResult: ScanResult,
) {

    val bluetoothDevice: BluetoothDevice
        get() = scanResult.device

    val name: String?
        get() = bluetoothDevice.name

    val address: String
        get() = bluetoothDevice.address

    val bondState: BondState
        get() = when (bluetoothDevice.bondState) {
            BOND_NONE -> BondState.None
            BOND_BONDING -> BondState.Bonding
            BOND_BONDED -> BondState.Bonded
            else -> error("Unknown bond state: ${bluetoothDevice.bondState}")
        }

    val rssi: Int
        get() = scanResult.rssi

    val txPower: Int?
        get() = scanResult.scanRecord?.txPowerLevel

    val uuids: List<Uuid>
        get() = scanResult.scanRecord?.serviceUuids?.map { it.uuid } ?: emptyList()

    fun serviceData(uuid: Uuid): ByteArray? =
        scanResult.scanRecord?.serviceData?.get(ParcelUuid(uuid))

    fun manufacturerData(companyIdentifierCode: Int): ByteArray? =
        scanResult.scanRecord?.getManufacturerSpecificData(companyIdentifierCode)

    val manufacturerData: Filter.ManufacturerData?
        get() = scanResult.scanRecord?.manufacturerSpecificData?.takeIf { it.size() > 0 }?.let {
            Filter.ManufacturerData(
                it.keyAt(0),
                it.valueAt(0)
            )
        }

    override fun toString(): String =
        "Advertisement(name=$name, bluetoothDevice=$bluetoothDevice, rssi=$rssi, txPower=$txPower)"
}