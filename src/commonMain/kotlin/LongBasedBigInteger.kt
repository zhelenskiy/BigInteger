import kotlin.jvm.JvmInline

@JvmInline
value class LongBasedBigInteger(private val value: Long) : BigInteger {

    override fun plus(other: BigInteger): BigInteger = when (other) {
        is LongBasedBigInteger -> plus(other)
        else -> error("compiler bug")
    }

    fun plus(other: LongBasedBigInteger): BigInteger = when {
        other.value > 0 -> if (this.value < Long.MAX_VALUE - other.value) LongBasedBigInteger(this.value + other.value) else TODO("No real big integer")
        other.value < 0 -> if (this.value > Long.MIN_VALUE - other.value) LongBasedBigInteger(this.value + other.value) else TODO("No real big integer")
        else -> this
    }

    infix fun plusExact(other: LongBasedBigInteger): LongBasedBigInteger = LongBasedBigInteger(sumLongsExact(this.value, other.value))
    
    infix fun plusExact(other: Long): LongBasedBigInteger = LongBasedBigInteger(sumLongsExact(this.value, other))
    
    infix fun plusExact(other: Int): LongBasedBigInteger = plusExact(other.toLong())

    override fun toString(): String = value.toString()
}

infix fun Long.plusExact(other: LongBasedBigInteger): LongBasedBigInteger = other plusExact this

infix fun Long.plusExact(other: Long) = sumLongsExact(this, other)

infix fun Int.plusExact(other: LongBasedBigInteger): LongBasedBigInteger = toLong() plusExact other

val Long.big get() = LongBasedBigInteger(this)
val Int.big get() = toLong().big
val UInt.big get() = toLong().big
internal expect fun sumLongsExact(a: Long, b: Long): Long
internal fun naiveSumLongsExact(a: Long, b: Long) = when {
    b > 0 -> if (a < Long.MAX_VALUE - b) a + b else throw ArithmeticException("long overflow")
    b < 0 -> if (a > Long.MIN_VALUE - b) a + b else throw ArithmeticException("long overflow")
    else -> a
}
