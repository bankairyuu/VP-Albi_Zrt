using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    public class MonthlyFees
    {
        public enum eFeeType { Water, Gas, Electricity, Internet }

        public int ID { get; set; }
        public DateTime CreationDate { get; set; }
        public eFeeType FeeType { get; set; }
        public int Amount { get; set; }

        public MonthlyFees() { }
    }
}
