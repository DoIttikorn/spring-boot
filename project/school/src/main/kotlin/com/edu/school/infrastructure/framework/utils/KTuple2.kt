package com.edu.school.infrastructure.framework.utils

import org.springframework.lang.NonNull
import reactor.util.function.Tuple2
import java.util.*

data class KTuple2<T1, T2>(
    @NonNull val component1: T1,
    @NonNull val component2: T2
) {
    companion object {
        fun <T1, T2> from(tuple: Tuple2<T1, T2>): KTuple2<T1, T2> {
            val component1 = Objects.requireNonNull(tuple.t1)
            val component2 = Objects.requireNonNull(tuple.t2)
            return KTuple2(component1, component2)
        }
    }
}
