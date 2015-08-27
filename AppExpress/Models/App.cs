using System;
using Android.App;

namespace AppExpress
{
    public class App
    {
        public Version InstalledVersion { get; }

        public Version ServerVersion { get; set; }

        public string Title { get; set; }

        readonly string id;

        public App(string id)
        {
            this.id = id;

            var pi = Application.Context.PackageManager.GetPackageInfo(id, 0);
            if (pi != null)
            {
                InstalledVersion = new Version(pi.VersionName);
            }
        }
    }
}