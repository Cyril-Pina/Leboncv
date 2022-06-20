package com.cyriltheandroid.leboncv.mapper

interface DataMapper<T, M> {
    fun map(obj: M): T
}