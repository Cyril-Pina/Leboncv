package com.cyriltheandroid.leboncv.data.model

enum class ContractType {
    PERMANENT {
        override fun getValue(): String = "CDI"
    },
    TEMPORARY {
        override fun getValue(): String = "CDD"
    },
    INTERNSHIP {
        override fun getValue(): String = "Stage"
    },
    WORK_STUDY {
        override fun getValue(): String = "Alternance"
    };

    abstract fun getValue(): String
}