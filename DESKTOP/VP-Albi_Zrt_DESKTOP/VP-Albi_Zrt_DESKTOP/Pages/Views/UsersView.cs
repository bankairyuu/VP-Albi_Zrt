using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Pages.Views
{
    public class UsersView
    {
        Model.User ReferenceUser;

        public int ID                       { get { return ReferenceUser.ID; }          set {  } }
        public string Name                  { get { return ReferenceUser.Name; }        set { ReferenceUser.Name = value; } }
        public string Username              { get { return ReferenceUser.Username; }    set {  } }
        public string Password              { get { return ReferenceUser.Password; }    set { ReferenceUser.Password = value; } }
        public string Phone                 { get { return ReferenceUser.Phone; }       set { ReferenceUser.Phone = value; } }
        public string CreditCardNumber { get { return ReferenceUser.CreditCardNumber; } set { ReferenceUser.CreditCardNumber = value; } }
        public string Nickname              { get { return ReferenceUser.Nickname; }    set { ReferenceUser.Nickname = value; } }
        public string Email                 { get { return ReferenceUser.Email; }       set { ReferenceUser.Email = value; } }

        public UsersView (Model.User u)
        {
            ReferenceUser = u;
        }
    }
}
