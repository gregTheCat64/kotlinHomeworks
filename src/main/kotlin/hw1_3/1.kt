package hw1_3


fun main() {
    val seconds = 28800

    println("пользователь был " + agoToText(seconds))

}

fun agoToText(seconds: Int): String {
    val aMinute = 60
    val anHour = aMinute * 60
    val aDay = anHour * 24

    val minutes = seconds / 60
    val hours = minutes / 60


    return when (seconds) {
        in 0 until aMinute -> "только что"
        in aMinute until anHour -> "$minutes" + " " + minutesToText(minutes) + " назад"
        in anHour..anHour * 8 -> "$hours" + " " + hoursToText(hours) + " назад" //в диапазоне до 8 часов, пишем "часов", если больше - то сегодня
        in anHour * 8 until aDay -> "сегодня"
        in aDay..aDay * 2 -> "вчера"
        else -> "давно"
    }
}

fun minutesToText(minutes: Int): String {
    val lastDigit = minutes % 10 //последняя цифра в числе
    return when {
        minutes in 11..19 -> "минут"
        lastDigit == 1 -> "минуту"
        lastDigit in 2..4 -> "минуты"
        else -> "минут"
    }
}

fun hoursToText(hours: Int):String {
    //я не беру диапозон 11..19, потому что не считаю количество часов больше 8
    return when (hours % 10) {
        1 -> "час"
        in 2..4 -> "часа"
        else -> "часов"
    }

}