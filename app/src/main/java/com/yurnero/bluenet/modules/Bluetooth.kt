package com.yurnero.bluenet.modules

import com.yurnero.bluenet.domain.BluetoothManager
import com.yurnero.bluenet.domain.BluetoothManagerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainComponent {
    @Binds
    fun provideBluetoothManager(impl: BluetoothManagerImp): BluetoothManager
}