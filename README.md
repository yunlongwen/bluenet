# bluenet
The purpose is to share the Internet capability of one device to the entire Bluetooth LAN.

- BlueTooth BLE 之 Central 与 Peripheral

Andorid 5.0 之前是无法进行 外围设备开发的，在Android 5.0 API 21 android.bluetooth.le包下，
新增加 Scaner相关类和 Advertiser 相关类。目前最后使用Scanner相关类实现蓝牙扫描。这段时间对蓝牙的学习与理解，对中心设备与周边设备做下面总结

1. Android BLE 周边设备 （Peripheral）可以通过 Advertiser 相关类实现操作；
2. Android BLE 中心设备 （Central）可以通过 Scanner相关类实现蓝牙扫描；
3. Android BLE 建立中心连接的时候，使用 BlueToothDevice#connectGatt() 实现