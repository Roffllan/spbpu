@file:Suppress("Stepan")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.math.*



// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

fun main(){
    /** Задача 1
    print("Введите возраст:")
    var voz:Int = readln().toInt()
    print(ageDescription(voz))
     */
    /**
    print(segmentLength(5,14,3,16))
    */
    /**
    print(timeForHalfWay(20.0, 15.0, 16.0, 25.0, 30.0, 50.0))
    */
    /**
     print(whichRookThreatens(1,2,5,7,1,3))
    */
    /**
    print(rookOrBishopThreatens(1,1,2,5,6,1))
    */
    /**
    print(triangleKind(6.0,6.0,3.0))
    */
    print(ageDescription(111))


}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    /** 0, 5-20 - лет
    1 - год
    2 - 4 - года */
    var a:Int = age
    if (!(a in 1..199)) return "Введите корректный возраст";
    if (a > 100) a %= 100
    if (a % 10 == 0 || a in (5..20) || a % 10 in (5..9)) return "$age лет";
    if (a % 10 == 1) return "$age год";
    return "$age года";

}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val halfS = (t1 * v1 + t2 * v2 + t3 * v3)/2
    return when {
        halfS <= t1 * v1 -> halfS / v1
        halfS <= t1 * v1 + t2 * v2 -> (halfS - t1 * v1) / v2 + t1
        else -> (halfS - t1 * v1 - t2 * v2) / v3 + t1 + t2
    }
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var dRook1 = kingX == rookX1 || kingY == rookY1
    var dRook2 = kingX == rookX2 || kingY == rookY2
    return when{
        dRook1 && dRook2 -> 3
        dRook1 -> 1
        dRook2 -> 2
        else -> 0
    }
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var dRook = kingX == rookX || kingY == rookY
    var dBishop = abs(kingX - bishopX) == abs(kingY - bishopY)
    return if (dRook && dBishop) 3
    else {
        if (dRook) return 1
        if (dBishop) return 2
        else return 0
    }
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var akv:Int = (a*a).toInt()
    var bkv:Int = (b*b).toInt()
    var ckv:Int = (c*c).toInt()
    return (
        if ((a + b < c) || (b + c < a) || (a + c < b)) -1
        else if ((akv == bkv + ckv) || (bkv == akv + ckv) || (ckv == akv + bkv)) 1
        else if ((akv > bkv + ckv) || (bkv > akv + ckv) || (ckv > akv + bkv)) 2
        else 0
            )
}


/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if ((a < c && b < c) || (c < a && d < a)) return -1
    else return abs((max(a, c) - min(b, d)))
}
