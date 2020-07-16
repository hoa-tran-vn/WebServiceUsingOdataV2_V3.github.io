using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using OneDuyKhanhDataAccess;
using System.Web.Http.OData.Builder;
using System.Web.Http.OData.Extensions;
using Microsoft.Data.Edm;
using Microsoft.Data.Edm.Csdl;

namespace MachineMonitorWebService
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            ODataModelBuilder builder = new ODataConventionModelBuilder();

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
            builder.EntitySet<ThoiGianMay_Thang>("ThoiGianMay_Thang");

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
