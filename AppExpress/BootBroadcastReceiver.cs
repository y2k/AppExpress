using Android.Content;
using Android.App;
using Android.OS;

namespace AppExpress
{
    [BroadcastReceiver]
    [IntentFilter(new [] { "android.intent.action.BOOT_COMPLETED" })]
    public class BootBroadcastReceiver : BroadcastReceiver
    {
        public override void OnReceive(Context context, Intent intent)
        {
            var i = new Intent(context, typeof(AndroidSyncService));
            var pi = PendingIntent.GetService(context, 0, i, PendingIntentFlags.UpdateCurrent);
            var am = (AlarmManager)context.GetSystemService(Context.AlarmService);
            am.SetRepeating(
                AlarmType.ElapsedRealtime,
                SystemClock.ElapsedRealtime() + 60 * 1000,
                3 * AlarmManager.IntervalHour, pi);
        }
    }
}