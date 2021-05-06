import kotlin.jvm.JvmInline

@JvmInline
value class LongBigInteger(private val value: Long) : BigInteger {
    override fun plus(other: BigInteger): BigInteger = when (other) {
        is LongBigInteger -> if (this.value < Long.MAX_VALUE - other.value) LongBigInteger(this.value + other.value) else TODO("No real big integer")
    }
    infix fun plusExact(other: LongBigInteger): LongBInteger =
        if (this.value < Long.MAX_VALUE - other.value) LongBigInteger(this.value + other.value) else throw ArithmeticException("${this + other} is out of Long bounds")

    override fun toString(): String = value.toString()
}

val Long.big get() = LongBigInteger(this)
val Int.big get() = toLong().big
val UInt.big get() = toLong().big