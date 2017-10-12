using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Pages.Views
{
    public class MonthlyFeesView
    {
        Model.MonthlyFees ReferenceMonthlyFees;

        public int ID
        {
            get { return ReferenceMonthlyFees.ID; }
            set { }
        }
        public DateTime CreationDate
        {
            get { return ReferenceMonthlyFees.CreationDate; }
            set { }

        }
        public Model.MonthlyFees.eFeeType FeeType
        {
            get { return ReferenceMonthlyFees.FeeType; }
            set { ReferenceMonthlyFees.FeeType = value; }
        }
        public int Amount
        {
            get { return ReferenceMonthlyFees.Amount; }
            set { ReferenceMonthlyFees.Amount = value; }
        }

        public MonthlyFeesView(Model.MonthlyFees mf)
        {
            ReferenceMonthlyFees = mf;
        }
    }
}
