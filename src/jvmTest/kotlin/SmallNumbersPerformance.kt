import kotlin.test.Test
import kotlin.time.*

private operator fun String.times(n: Int) = repeat(n)

class SmallNumbersPerformance {
    @ExperimentalTime
    @Test
    fun compareAddition() {
        val maxN = 10_000_0000
        val javaMathBigIntegers = ArrayList<Duration>()
        val kotlinGenericBigIntegers = ArrayList<Duration>()
        val kotlinLongBasedBigIntegers = ArrayList<Duration>()
        val pureInts = ArrayList<Duration>()
        val pureLongs = ArrayList<Duration>()
        val checkedLongs = ArrayList<Duration>()
        repeat(5) {
            javaMathBigIntegers.add(measureTime {
                var a = 0.toBigInteger()
                for (n in 1..maxN)
                    a += 5.toBigInteger()
            })
            kotlinGenericBigIntegers.add(measureTime {
                var a = 0.big as BigInteger
                for (n in 1..maxN)
                    a += 5.big
            })
            kotlinLongBasedBigIntegers.add(measureTime {
                var a = 0.big
                for (n in 1..maxN)
                    a = a plusExact 5.big
            })
            pureInts.add(measureTime {
                var a = 0
                for (n in 1..maxN)
                    a += 5
            })
            pureLongs.add(measureTime {
                var a = 0L
                for (n in 1..maxN)
                    a += 5L
            })
            checkedLongs.add(measureTime {
                var a = 0L
                for (n in 1..maxN)
                    a = a plusExact 5L
            })
            println()
        }
        fun join(durations: List<Duration>) = durations.joinToString("\t\t")
        println("java.lang.Math.BigInteger:\t${join(javaMathBigIntegers)}")
        println("Kotlin (Generic):${"\t" * 3}${join(kotlinGenericBigIntegers)}")
        println("Kotlin (LongBased):${"\t" * 3}${join(kotlinLongBasedBigIntegers)}")
        println("Pure Int:${"\t" * 5}${join(pureInts)}")
        println("Pure Long:${"\t" * 5}${join(pureLongs)}")
        println("Checked Long:${"\t" * 4}${join(checkedLongs)}")
    }
}
