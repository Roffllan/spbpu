@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import ru.spbstu.wheels.NullableMonad.map
import java.lang.Math.pow
import kotlin.math.*


// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

fun main(){

    //print(digitNumber(1))

    /**
     print(revert(1234567))
     */
    /** (print (isPalindrome(23432)) */
    /**print(fib(1))*/
    /**print(minDivisor(21))*/
    /**print(maxDivisor(1231))*/
    /**print(collatzSteps(15))*/
    /**print(lcm(3, 7))*/
    /**print(isCoPrime(17, 13))*/
    /**print(hasDifferentDigits(555))*/
    print(squareSequenceDigit(17))
    //assertEquals(1, squareSequenceDigit(1))
    //        assertEquals(4, squareSequenceDigit(2))
    //        assertEquals(5, squareSequenceDigit(7))
    //        assertEquals(6, squareSequenceDigit(12))
    //        assertEquals(0, squareSequenceDigit(17))
    //        assertEquals(9, squareSequenceDigit(27))


}
/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var kol = 0
    var num = n
    while (num != 0) {
        kol++
        num /= 10
    }
    return kol
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a:Int = 1
    var b:Int = 1
    for (el in 3..n){
        if (el % 2 == 0) a +=b
        else b += a
    }
    return if (a > b) a else b
}
/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var count:Int = 1
    while (!(n % ++count == 0));
    return count

}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var count:Int = 1
    while (!(n % ++count == 0));
    return n / count
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count:Int = 0
    var step:Int = x
    while(step != 1){
        if (step % 2 == 0) step = step / 2
        else step = 3 * step + 1
        ++count
    }
    return count

}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var count:Int = 1
    while (m >= ++count && n >= ++count) if (m % count == 0 && n % count == 0) return count
    return 1
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    return if (lcm(m, n) == 1) true else false
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var num:Int = 0
    var del = n
    while (true){
        num += del % 10
        del /= 10
        if (del == 0) return num
        num *= 10
    }
    return num
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    /** 3223 */
    return (if(n == revert(n)) true else false )
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num:Int = n
    var digit:Int = n % 10
    while (num > 0) if (num % 10 == digit) num /= 10 else return false
    return true
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    /**digitNumber кол-во цифр в числе*/
    var count:Int = 0
    var num:Int = 1
    var i = 1
    while (n > count){ //пока счётчик меньше чем необходимое число
        count += digitNumber(i*i) //собираем кол-во цифр
        var j:Int = 0 //счётчик для внутреннего массивa
        while (digitNumber(i*i)>=++j) { //пока счётчик меньше кол-ва цифр в квадрате
            num *= 10 //добавляем кол-во нулей для новых квадратов
            //print("$count $num \n")
        }
        num += i*i //добавляем новый квадрат в переменную
        j = 0
        var num1 = 0
        while (digitNumber(i*i)>++j) { //пока счётчик меньше кол-ва цифр в квадрате
            num1 = (num.toDouble()/10).toInt()//добавляем кол-во нулей для новых квадратов
            //print("$count $num \n")
        }
        num = num - num * (Math.pow(10.0, digitNumber(i*i).toDouble())).toInt()

        print("count=$count num=$num i=$i  \n")
        ++i//инкремент числа, которе возводим в квадрат
    }
    print("\n\n$count $num \n")
    num = revert(num) //разворачиваем порядок чисел
    while (digitNumber(num) > n)num /= 10 // отнимаем кол-во цифр до указанного значения
    return num

}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
