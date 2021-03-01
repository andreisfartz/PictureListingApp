package com.asfartz.picturelistingapp.hermetic

import com.asfartz.picturelistingapp.MyApplication
import org.mockito.Mockito

class TestApplication: MyApplication() {

    private val clock: Clock by lazy {
        Mockito.mock(Clock::class.java)
    }

    override fun provideClock(): Clock = clock


}