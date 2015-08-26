using Android.App;
using Android.OS;
using Android.Preferences;

namespace AppExpress
{
    [Activity(Label = "AppExpress", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : PreferenceActivity
    {
        AppService apps = new AppService();

        protected async override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            AddPreferencesFromResource(Resource.Xml.settings);
            foreach (var app in await apps.GetAppsAsync())
            {
                PreferenceScreen.AddPreference(
                    new CheckBoxPreference(this)
                    {
                        Title = app.Title,
                        Summary = "ver " + app.Version,
                    });
            }
        }
    }
}