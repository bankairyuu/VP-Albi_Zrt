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

        public int ID { get; set; }
        public DateTime CreationTime { get; set; }
        public User From { get; set; }
        public User To { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public eSharingType SharingType { get; set; }
        public int Amount { get; set; }
        public bool ApartmentCost { get; set; }
        public DateTime ExpenseMonth { get; set; }
        public bool Paid { get; set; }

        public Expense() { }
    }
}
