using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using OneDuyKhanhDataAccess;
using System.Web.Http.OData.Builder;
using System.Web.Http.OData.Extensions;
using Microsoft.Data.Edm;
using Microsoft.Data.Edm.Csdl;
using System.Net.Http;
using System.Net.Http.Headers;

namespace MachineMonitorService
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            ODataModelBuilder builder = new ODataConventionModelBuilder();
            /*
                        using (HttpClient httpClient = new HttpClient())
                        {
                            //Setup Accept Header
                            MediaTypeWithQualityHeaderValue acceptHeader = MediaTypeWithQualityHeaderValue.Parse("application/json;odata=verbose");
                            httpClient.DefaultRequestHeaders.Accept.Add(acceptHeader);

                            //... do other stuff
                        }
            */

            Version odataVersion1 = new Version(1, 0);
            Version odataVersion3 = new Version(3, 0);
            builder.DataServiceVersion = odataVersion1;
            builder.MaxDataServiceVersion = odataVersion3;

            

            builder.EntitySet<BoGiamSat>("BoGiamSats");
            builder.EntitySet<CongViec>("CongViecs");
            builder.EntitySet<LichSuMay>("LichSuMays");
            builder.EntitySet<May>("Mays");
            builder.EntitySet<NhanVien>("NhanViens");
            builder.EntitySet<SuaChua>("SuaChuas");
            builder.EntitySet<ThoiGianMay>("ThoiGianMays");
            builder.EntitySet<TinhTrangMay>("TinhTrangMays");

            IEdmModel edmModel = builder.GetEdmModel();
            edmModel.SetEdmVersion(odataVersion1);
            edmModel.SetEdmxVersion(odataVersion1);


            config.MapHttpAttributeRoutes();

            config.Routes.MapODataServiceRoute("odata", null, edmModel);

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
                );
        }
    }
}
