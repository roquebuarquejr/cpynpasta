package com.roquebuarque.cpynpasta.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class LifecycleScope : DefaultLifecycleObserver, CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onPause(owner: LifecycleOwner) {
        coroutineContext.cancel()
        super.onPause(owner)
    }
}
