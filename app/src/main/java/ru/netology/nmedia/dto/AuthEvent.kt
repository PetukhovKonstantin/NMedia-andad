package ru.netology.nmedia.dto

sealed class AuthEvent {
    object LoggedIn : AuthEvent()
    object LoggedOut : AuthEvent()
}