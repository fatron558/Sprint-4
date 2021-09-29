package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filter {
            ZonedDateTime.now(ZoneId.of(it)).minute != ZonedDateTime.now(ZoneId.of("UTC")).minute
        }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values()
        .map {
            LocalDate.of(year, it, 1)
                .with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString()
        }
        .toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values()
        .map { LocalDate.of(year, it, 13).dayOfWeek }
        .filter { it == DayOfWeek.FRIDAY }
        .count()
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm", Locale.US))
}



