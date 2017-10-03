using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    class Expense
    {
        public enum eSharingType { Personal, Common };

        int _ID;
        DateTime _CreationTime;
        User _From;
        User _To;
        string _Name;
        string _Description;
        eSharingType _SharingType;
        int _Amount;
        bool _ApartmentCost;
        DateTime _ExpenseMonth;
        bool _Paid;

        public int ID { get => _ID; set => _ID = value; }
        public DateTime CreationTime { get => _CreationTime; set => _CreationTime = value; }
        public User From { get => _From; set => _From = value; }
        public User To { get => _To; set => _To = value; }
        public string Name { get => _Name; set => _Name = value; }
        public string Description { get => _Description; set => _Description = value; }
        public eSharingType SharingType { get => _SharingType; set => _SharingType = value; }
        public int Amount { get => _Amount; set => _Amount = value; }
        public bool ApartmentCost { get => _ApartmentCost; set => _ApartmentCost = value; }
        public DateTime ExpenseMonth { get => _ExpenseMonth; set => _ExpenseMonth = value; }
        public bool Paid { get => _Paid; set => _Paid = value; }

        public Expense(int ID, DateTime CreationTime, User From, User To, string Name, string Description, eSharingType SharingType, int Amount, bool ApartmentCost, DateTime ExpenseMonth, bool Paid)
        {
            _ID = ID;
            _CreationTime = CreationTime;
            _From = From;
            _To = To;
            _Name = Name;
            _Description = Description;
            _SharingType = SharingType;
            _Amount = Amount;
            _ApartmentCost = ApartmentCost;
            _ExpenseMonth = ExpenseMonth;
            _Paid = Paid;
        }

        public Expense() { }
    }
}
