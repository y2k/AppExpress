package y2k.appexpress.models

import android.os.Handler
import rx.schedulers.Schedulers

//
// Created by y2k on 1/1/16.
//
object UIScheduler {

    private val handler = Handler()

    val scheduler = Schedulers.from { handler.post(it) }
}