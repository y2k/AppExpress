using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DropNetRT;

namespace AppExpress
{
    public class AppService
    {
        public async Task<List<App>> GetAppsAsync()
        {
            var client = new DropNetClient(
                             Settings.Instance.Get("api-key"),
                             Settings.Instance.Get("api-secret"),
                             Settings.Instance.Get("access-token"),
                             Settings.Instance.Get("access-secret"));
            client.UseSandbox = true;

            var meta = await client.GetMetaData("/");
            return meta.Contents
                .Select(s => new App { Title = s.Name })
                .ToList();
        }
    }
}