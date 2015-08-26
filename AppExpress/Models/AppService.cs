using System;
using System.Threading.Tasks;
using System.Collections.Generic;

namespace AppExpress
{
    public class AppService
    {
        public event EventHandler ListChanged;

        public async Task<List<App>> GetAppsAsync()
        {
            await Task.Delay(1000);

            return new List<App>
            {
                new App { Title = "Spectator", Version = new Version("0.9.1") },
                new App { Title = "JoyReactor", Version = new Version("1.0") },
                new App { Title = "AppExpress", Version = new Version("0.9.055") },
            };
        }
    }
}