package com.cyriltheandroid.leboncv

enum class LanguageLevel {
    A1 {
        override fun getValue() = "Débutant"
    },
    A2 {
        override fun getValue() = "Elémentaire"
    },
    B1 {
        override fun getValue() = "Intermédiaire"
    },
    B2 {
        override fun getValue() = "Intermédiaire avancé"
    },
    C1 {
        override fun getValue() = "Avancé"
    },
    C2 {
        override fun getValue() = "Bilingue"
    },
    NATALE {
        override fun getValue() = "Natale"
    };

    abstract fun getValue(): String
}
