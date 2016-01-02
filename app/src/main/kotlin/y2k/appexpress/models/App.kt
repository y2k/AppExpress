package y2k.appexpress.models

//
// Created by y2k on 1/1/16.
//
class App(
    val title: String,
    val packageName: String,
    val serverVersion: Version,
    val installedVersion: Version?) {

    //    var installedVersion: Version? = null
    var locked = false

    val installed: Boolean
        get() = installedVersion != null

    val id: Long
        get() = packageName.hashCode().toLong()

    val state: State
        get() {
            return when {
                locked -> State.InProgress
                installedVersion == null -> State.NotInstalled
                installedVersion < serverVersion -> State.HasUpdate
                else -> State.Actual
            }
        }

    enum class State {
        NotInstalled, Actual, HasUpdate, InProgress
    }
}