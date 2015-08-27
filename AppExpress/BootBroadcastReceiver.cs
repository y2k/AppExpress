using Android.App;
using Android.Content;

namespace AppExpress
{
    [BroadcastReceiver]
    [IntentFilter(new [] { "android.intent.action.BOOT_COMPLETED" })]
    public class BootBroadcastReceiver : BroadcastReceiver
    {
        public override void OnReceive(Context context, Intent intent)
        {
            AndroidSyncService.InitializeService(context);
        }
    }
}