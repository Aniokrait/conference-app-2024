package io.github.droidkaigi.confsched.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual typealias KmpViewModel = ViewModel

actual val KmpViewModel.viewModelScope: CoroutineScope
    get() = this.viewModelScope
