package com.example.ui_components.models.payment_methods.core


enum class CardProvider(val isBank: Boolean = true) {
    ScotiaBank, Ncb, JMMB
}