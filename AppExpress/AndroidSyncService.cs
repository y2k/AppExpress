using System;
using Android.App;
using Android.Content;
using Android.OS;

namespace AppExpress
{
    [Service]
    public class AndroidSyncService : Service
    {
        public override IBinder OnBind(Intent intent)
        {
            throw new NotImplementedException();
        }

        public override StartCommandResult OnStartCommand(Intent intent, StartCommandFlags flags, int startId)
        {
            HandleCommand();
            return StartCommandResult.Sticky;
        }

        async void HandleCommand()
        {
            await new SyncService().Sync();
            StopSelf();
        }

        public static void InitializeService(Context context)
        {
            var i = new Intent(context, typeof(AndroidSyncService));
            var pi = PendingIntent.GetService(context, 0, i, PendingIntentFlags.UpdateCurrent);
            var am = (AlarmManager)context.GetSystemService(Context.AlarmService);
            am.SetInexactRepeating(
                AlarmType.ElapsedRealtime,
                SystemClock.ElapsedRealtime() + 60 * 1000, 
                3 * AlarmManager.IntervalHour, pi);
        }
    }
}