package com.cyriltheandroid.leboncv.data.mapper

interface DataMapper<T, M> {
    fun map(obj: M): T
}