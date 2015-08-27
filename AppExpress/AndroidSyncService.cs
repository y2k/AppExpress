using System;
using Android.App;
using Android.Content;

namespace AppExpress
{
    [Service]
    public class AndroidSyncService : Service
    {
        public override Android.OS.IBinder OnBind(Intent intent)
        {
            throw new NotImplementedException();
        }

        [Obsolete("deprecated")]
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
    }
}