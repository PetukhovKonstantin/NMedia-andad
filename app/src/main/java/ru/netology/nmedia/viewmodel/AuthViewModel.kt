package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import ru.netology.nmedia.auth.AppAuth
import ru.netology.nmedia.auth.AuthState
import ru.netology.nmedia.dto.AuthEvent
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth: AppAuth) : ViewModel() {
    val data: LiveData<AuthState> = auth.authStateFlow
        .asLiveData(Dispatchers.Default)
    val authenticated: Boolean
        get() = auth.authStateFlow.value.id != 0L

    private val _authEvent = MutableLiveData<AuthEvent>()
    val authEvent: LiveData<AuthEvent> = _authEvent

    fun login(id: Long, token: String) {
        auth.setAuth(id, token)
        _authEvent.value = AuthEvent.LoggedIn
    }

    fun logout() {
        auth.removeAuth()
        _authEvent.value = AuthEvent.LoggedOut
    }
}
