@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.minDivisor
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")
fun main(){
    //print(mean(listOf()))
    //print(center(mutableListOf()))
    //print(times(listOf(), listOf()))
    //print(polynom(listOf(1, 2, 3),5))
    //print(abs(listOf(3.0, 4.0)))
    //print(accumulate(mutableListOf(1,2,3,4)))
    //print(factorize(75))
    //print(factorizeToString(75))
    //print(convert(100, 4))
    print(russian(103000))
}

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var summ:Double = 0.0
    for (el in v) summ += el.pow(2.0)
    return sqrt(summ)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var summ:Double = 0.0
    for (el in list) summ += el
    return if (summ != 0.0) summ / list.size else 0.0

}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.size == 0) return list
    val sp:Double = mean(list)
    for (el in list.indices) list[el] -= sp
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var count:Int = 0
    var summ:Int = 0
    while (count < a.size) summ += a[count] * b[count++]
    return summ
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var count:Int = -1
    var summ:Int = 0
    while(++count < p.size) summ += p[count] * Math.pow(x.toDouble(),count.toDouble()).toInt()
    return summ


}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int>{
    var count:Int = 0
    while (++count < list.size) list[count]+=list[count-1]
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val sp = mutableListOf<Int>()
    var num:Int = n
    var count:Int = 1
    while (num >= ++count) {
        if (num % count == 0){
            num = num / count
            sp.add(count)
            count = 1
        }
    }
    return sp
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    return factorize(n).joinToString("*")
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var digit:Int = n
    val sp = mutableListOf<Int>()
    while (digit > 0){
        sp.add(0,digit % base)
        digit = digit / base
    }
    return sp
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val letters:String = "abcdefghijklmnopqrstuvwxyzABC"
    var res = ""
    var dig:Int = n
    if (1 > base ||  base > 37) return "-1"

    while (dig > 0) {
        if (dig % base > 9) res += letters[dig % base - 10]
        else res += dig % base
        dig /= base
    }

    return res.reversed()

}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var digits1: List<Int> = digits.reversed()
    var count:Int = -1
    var res:Int = 0
    while (++count < digits1.size) res += digits1[count] * Math.pow(base.toDouble(),count.toDouble()).toInt()
    return res
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var summ:Int = 0
    var count:Int = str.length
    val letters:String = "0123456789abcdefghijklmnopqrstuvwxyzABC"
    for (x in str) summ += letters.indexOf(x) * pow(base*1.0,--count*1.0).toInt()
    return summ
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val count_str:List<String> = listOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X")
    val decards_str:List<String> = listOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C")
    val hund_str:List<String> = listOf("", "С", "СС", "ССС", "СD", "D", "DC", "DCC", "DCCC", "CM", "M")
    val thou_str:List<String> = listOf("", "M", "MM", "MMM")
    var res:String = ""
    var digit:String = n.toString()
    while (digit.length != 0){
        if (digit.length == 4) res += thou_str[digit[0].digitToInt()]
        if (digit.length == 3) res += hund_str[digit[0].digitToInt()];
        if (digit.length == 2) res += decards_str[digit[0].digitToInt()];
        if (digit.length == 1) res += count_str[digit[0].digitToInt()];
        digit = digit.substring(1)
    }
    return res
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val dig1:List<String> = listOf("", " один", " два", " три", " четыре", " пять", " шесть", " семь", " восемь", " девять")
    val dig2:List<String> = listOf("", " одиннадцать", " двенадцать", " тринадцать", " четырнадцать", " пятнадцать", " шестнадцать", " семнадцать", " восемнадцать", " девятнадцать")
    val dig3:List<String> = listOf(""," десять", " двадцать", " тридцать", " сорок", " пятьдесят", " шестьдесят", " семьдесят", " восемьдесят", " девяносто")
    val dig4:List<String> = listOf("", " сто", " двести", " триста", " четыреста", " пятьсот", " шестьсот", " семьсот", " восемьсот", " девятьсот")
    val dig5:List<String> = listOf(" тысяч", " тысяча", " тысячи")
    val dig6:List<String> = listOf("", " одна", " две")
    var res:String = ""
    var digit:String = n.toString()
    while (digit.length != 0){
        if (digit.length == 1 || digit.length == 4) {
            if (digit.length == 4 && digit[0].digitToInt() in 1..2){
                if (digit[0].digitToInt() == 1) res += dig6[1]
                if (digit[0].digitToInt() == 2) res += dig6[2]
            }
            else res += dig1[digit[0].digitToInt()]
            if (digit.length == 4)
                if (digit[0].digitToInt() == 1) res += dig5[1]
                else if (digit[0].digitToInt() in 2..4) res += dig5[2]
                else res += dig5[0]
        }
        if (digit.length == 2 || digit.length == 5)
            if (digit[0].digitToInt() == 1) {
                res += dig2[digit[1].digitToInt()]
                if (digit.length == 5) res += dig5[0]
                digit = digit.substring(1)
            }
            else res += dig3[digit[0].digitToInt()]
        if (digit.length == 3 || digit.length == 6) res += dig4[digit[0].digitToInt()]
        digit = digit.substring(1)
    }
    return res.substring(1)
}