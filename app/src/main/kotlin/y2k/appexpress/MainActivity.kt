package y2k.appexpress

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import y2k.appexpress.models.App
import y2k.appexpress.models.AppService
import y2k.appexpress.models.PackageService
import y2k.appexpress.models.StorageService

class MainActivity : AppCompatActivity() {

    val service = AppService(PackageService(this), StorageService())
    var items = emptyList<App>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById(R.id.list) as RecyclerView
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = AppAdapter()

        service
            .getApps()
            .subscribe({
                items = it
                list.adapter.notifyDataSetChanged()
            }, { it.printStackTrace() })
    }

    inner class AppAdapter : RecyclerView.Adapter<ViewHolder>() {

        init {
            setHasStableIds(true)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_app, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val app = items[position]
            holder.title.text = "${app.title} (${app.id})"
            holder.subTitle.text = "Version: ${app.serverVersion}"
            holder.installed.isChecked = app.installed
        }

        override fun getItemId(position: Int): Long {
            return items[position].intId
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.title) as TextView
        val subTitle = view.findViewById(R.id.subTitle) as TextView
        val installed = view.findViewById(R.id.installed) as CheckBox
    }
}