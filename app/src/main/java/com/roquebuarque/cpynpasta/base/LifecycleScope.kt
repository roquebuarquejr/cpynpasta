package com.roquebuarque.cpynpasta.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class LifecycleScope : DefaultLifecycleObserver, CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onPause(owner: LifecycleOwner) {
        coroutineContext.cancel()
        super.onPause(owner)
    }
}
