using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    class MonthlyFees
    {
        public enum eFeeType { Water, Gas, Electricity, Internet }

        int _ID;
        DateTime _CreationDate;
        eFeeType _FeeType;
        int _Amount;

        public int ID { get => _ID; set => _ID = value; }
        public DateTime CreationDate { get => _CreationDate; set => _CreationDate = value; }
        public eFeeType FeeType { get => _FeeType; set => _FeeType = value; }
        public int Amount { get => _Amount; set => _Amount = value; }

        public MonthlyFees(int ID, DateTime CreationDate, eFeeType FeeType, int Amount)
        {
            _ID = ID;
            _CreationDate = CreationDate;
            _FeeType = FeeType;
            _Amount = Amount;
        }
    }
}
