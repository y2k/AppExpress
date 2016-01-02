package y2k.appexpress.common

import rx.Observable

//
// Created by y2k on 1/2/16.
//
fun <T> Observable<T?>.notNull(): Observable<T> {
    return filter { it != null }.map { it!! }
}