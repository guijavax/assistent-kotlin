package com.api.assistent.assistentkotlin.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

internal class DateOperations {

    companion object {

        private fun getLocalDateNow() : LocalDate = LocalDate.now()

        fun convertLocalDateToDate() : Date {
            return Date.from(getLocalDateNow().atStartOfDay(ZoneId.systemDefault()).toInstant())
        }

        fun plusDate(months : Long) : Date {
            return Date.from(getLocalDateNow().plus(months, ChronoUnit.MONTHS).atStartOfDay(ZoneId.systemDefault()).toInstant())
        }
    }
}