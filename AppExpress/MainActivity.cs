using Android.App;
using Android.OS;
using Android.Preferences;

namespace AppExpress
{
    [Activity(Label = "AppExpress", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : PreferenceActivity
    {
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);
            AddPreferencesFromResource(0);
        }
    }
}