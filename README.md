# BigInteger


| Adding small numbers via | 1 | 2 | 3 | 4 | 5 |
| :- | - | - | - | - | - |
| `java.lang.Math.BigInteger` |	1.94s	|	2.31s	|	2.15s	|	2.12s	|	2.49s |
| Kotlin (Generic)  |	508ms |	730ms	|	912ms	|	553ms	|	734ms |
| Kotlin (LongBased)	|	67.1ms	|	52.7ms	|	67.8ms	|	57.3ms	|	55.7ms |
| Pure Int | 8.25ms | 4.69us | 2.03us | 1.71us | 1.75us |
| Pure Long | 12.1ms | 4.01ms | 5.10ms | 5.07ms | 4.28ms |
| Checked Long | 63.1ms | 67.2ms | 69.0ms | 65.4ms | 56.9ms |

Even generic code that stores the number separatedly if it is small helps to make BigInt 4 times faster than java math version.

Explicit using of `LongBased` type  makes evaluation 10 times faster. This works because of `@JvmInline`. That is why the results for `Checked Long` are the same.

Pure `Int` and `Long` are a lot faster but overflowable.

