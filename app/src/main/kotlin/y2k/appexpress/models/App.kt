package y2k.appexpress.models

/**
 * Created by y2k on 1/1/16.
 */
class App(val title: String, val id: String, val serverVersion: String) {

    lateinit var installedVersion: String
    var installed: Boolean = false

    val intId: Long
        get() = id.hashCode().toLong()
}