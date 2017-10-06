using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    public class User
    {
        public User() { }

        public int ID { get; set;}
        public string Name { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Phone { get; set; }
        public string CreditCardNumber { get; set; }
        public string Nickname { get; set; }
        public string Email { get; set; }
    }
}
