using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    class User
    {
        int _ID;
        string _Name;
        string _Username;
        string _Password;
        string _Phone;
        string _CreditCardNumber;
        string _Nickname;
        string _Email;

        public User(int ID, string Name, string Username, string Password, string Phone, string CreditCardNumber, string Nickname, string Email)
        {
            _ID = ID;
            _Name = Name;
            _Username = Username;
            _Password = Password;
            _Phone = Phone;
            _CreditCardNumber = CreditCardNumber;
            _Nickname = Nickname;
            _Email = Email;
        }

        public User() { }

        public int ID { get => _ID; set => _ID = value; }
        public string Name { get => _Name; set => _Name = value; }
        public string Username { get => _Username; set => _Username = value; }
        public string Password { get => _Password; set => _Password = value; }
        public string Phone { get => _Phone; set => _Phone = value; }
        public string CreditCardNumber { get => _CreditCardNumber; set => _CreditCardNumber = value; }
        public string Nickname { get => _Nickname; set => _Nickname = value; }
        public string Email { get => _Email; set => _Email = value; }
    }
}
