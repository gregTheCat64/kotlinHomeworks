package hw1_3

//Комиссию оставил в рублях, поскольку удобнее для понимания (как правило комиссия больше 35 рублей)
//мы не проходили как вкладывать различные структуры друг в друга типа when и if - else
//сделал, так как показалось наиболее логичным и охватывающим почти все условия(за исключением суммы суточных платежей - их вложил в разовый платеж)
//Если был другой более легкий и логичный способ, прошу поделиться

fun main() {

    println("Сумма вашей комиссии составила, руб.: ")

    println(calculateFee(sumOfThisTransfer = 1000, typeOfPay = "VkPay", sumOfPreviousTransfers = 38000)) //проходим
    println(calculateFee(sumOfThisTransfer = 1000, typeOfPay = "VkPay", sumOfPreviousTransfers = 39000)) //превысили лимит за месяц
    println(calculateFee(sumOfThisTransfer = 16000, typeOfPay = "VkPay"))//превысили лимит развого платежа
    println(calculateFee(sumOfThisTransfer = 1000, typeOfPay = "Visa"))  //проходим, комиссия 75 рублей
    println(calculateFee(sumOfThisTransfer = 10_000, typeOfPay = "Visa", sumOfPreviousTransfers = 590_000)) //превысили лимит в 600_000
    println(calculateFee(sumOfThisTransfer = 151_000, typeOfPay = "Mir")) //превысили суточный лимит
    println(calculateFee(sumOfThisTransfer = 200, typeOfPay = "Mir")) //проходим, минимальная комиссия 35 рублей
    println(calculateFee(sumOfThisTransfer = 151_000, typeOfPay = "Maestro", sumOfPreviousTransfers = 450_000)) //превысили все лимиты
    println(calculateFee(sumOfThisTransfer = 50_000, typeOfPay = "Maestro")) //проходим, комиссия 3000+20 рублей

}


fun checkLimits(
    typeOfPay: String = "VkPay",
    sumOfPreviousTransfers: Int = 0,
    sumOfThisTransfer: Int
): Boolean {
   return when (typeOfPay) {
       //сумма разового перевода условно ограничил 150_000, сумма предыдущих переводов и этого не более 600_000
        "Maestro", "Mastercard", "Visa", "Mir" -> sumOfThisTransfer < 150_000 && (sumOfPreviousTransfers + sumOfThisTransfer) < 600_000
       //для вк пэй - разово до 15000, сумма всех - до 40_000
        "VkPay" -> sumOfThisTransfer < 15_000 && (sumOfPreviousTransfers + sumOfThisTransfer) < 40_000
        else -> false
   }

}

fun calculateFee(
    typeOfPay: String = "VkPay",
    sumOfPreviousTransfers: Int = 0,
    sumOfThisTransfer: Int
): Double {


// проверяем допустимость перевода:
    if (checkLimits(typeOfPay, sumOfPreviousTransfers, sumOfThisTransfer)) {
       return when (typeOfPay) {
            "Maestro", "Mastercard" -> if (sumOfPreviousTransfers < 75_000) return sumOfThisTransfer * 0.06 + 20 else 0.00
            "Visa", "Mir" -> if (sumOfThisTransfer * 0.075 < 35) return 35.0 else 0.075 * sumOfThisTransfer

           else -> 0.00 //если вк - то комиссия 0
       }

    }     else
        println("Платеж превысил лимит. Попробуйте позже")
        return -1.0 //если платеж недопустим

}