using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AppExpress
{
    public class Settings
    {
        public static Settings Instance { get; } = new Settings();

        Dictionary<string, string> data = new Dictionary<string, string>();

        Settings()
        {
            using (var stream = GetType().Assembly.GetManifestResourceStream("AppExpress.private-settings.ini"))
            {
                var reader = new StreamReader(stream);
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    var parts = line.Split('=').Select(s => s.Trim()).ToList();
                    data[parts[0]] = parts[1];
                }
            }
        }

        public string Get(string key)
        {
            return data[key];
        }
    }
}