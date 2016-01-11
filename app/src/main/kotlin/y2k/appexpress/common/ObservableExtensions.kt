package y2k.appexpress.common

import rx.Observable
import java.util.*

//
// Created by y2k on 1/2/16.
//
fun <T> Observable<T?>.notNull(): Observable<T> {
    return filter { it != null }.map { it!! }
}

fun <T> Observable<T>.accumulate(): Observable<List<T>> {
    val buffer = Collections.synchronizedList(ArrayList<T>())
    return map {
        buffer.add(it)
        buffer
    }
}