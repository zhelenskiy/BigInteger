import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class SmallNumbersPerformance {
    @ExperimentalTime
    @Test
    fun compareAddition() {
        val maxN = 1000_0000
        repeat(5) {
            measureTimedValue {
                var a = 0.toBigInteger()
                for (n in 1..maxN)
                    a += 5.toBigInteger()
                a
            }.also { println("Java:\t$it") }
            measureTimedValue {
                var a = 0.big as BigInteger
                for (n in 1..maxN)
                    a += 5.big
                a
            }.also { println("Kotlin:\t$it") }
            measureTimedValue {
                var a = 0.big
                for (n in 1..maxN)
                    a = (a + 5.big) as LongBigInteger
                a
            }.also { println("KotlinLong:\t$it") }
            measureTimedValue {
                var a = 0L
                for (n in 1..maxN)
                    a += 5L
                a
            }.also { println("Long:\t$it") }
            measureTimedValue {
                var a = 0
                for (n in 1..maxN)
                    a += 5
                a
            }.also { println("Int:\t$it") }
            println()
        }
    }
}