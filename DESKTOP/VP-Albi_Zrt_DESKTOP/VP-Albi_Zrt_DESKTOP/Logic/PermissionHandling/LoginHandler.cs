using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VP_Albi_Zrt_DESKTOP.Model;

namespace VP_Albi_Zrt_DESKTOP.Logic.PermissionHandling
{
    public static class LoginHandler
    {
        private static Model.User LoggedInUser;
        public static string LoggedInUserName;

        public static bool Login(string u, string p)
        {
            try
            {
                LoggedInUser = DatabaseConnector.DatabaseConnector.SearchUser(u);
                if (LoggedInUser.Password != p) return false;
                LoggedInUserName = LoggedInUser.Username;
                return true;
            }
            catch { }
            return false;
        }
    }
}
