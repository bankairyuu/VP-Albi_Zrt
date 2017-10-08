using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Pages.Views
{
    public class ExpensesView
    {
        Model.Expense ReferenceExpense;

        public int ID
        {
            get { return ReferenceExpense.ID; }
            set { }
        }
        public DateTime CreationTime
        {
            get { return ReferenceExpense.CreationTime; }
            set { }
        }
        public string From
        {
            get { return ReferenceExpense.From.Name; }
            set { }
        }
        public string To
        {
            get { return ReferenceExpense.To.Name; }
            set { }
        }
        public string Name
        {
            get { return ReferenceExpense.Name; }
            set { ReferenceExpense.Name = value; }
        }
        public string Description
        {
            get { return ReferenceExpense.Description; }
            set { ReferenceExpense.Description = value; }
        }
        public Model.Expense.eSharingType SharingType
        {
            get { return ReferenceExpense.SharingType; }
            set { ReferenceExpense.SharingType = value; }
        }
        public int Amount
        {
            get { return ReferenceExpense.Amount; }
            set { ReferenceExpense.Amount = value; }
        }
        public bool ApartmentCost
        {
            get { return ReferenceExpense.ApartmentCost; }
            set { ReferenceExpense.ApartmentCost = value; }
        }
        public DateTime ExpenseMonth
        {
            get { return ReferenceExpense.ExpenseMonth; }
            set { ReferenceExpense.ExpenseMonth = value; }
        }
        public bool Paid
        {
            get { return ReferenceExpense.Paid; }
            set { ReferenceExpense.Paid = value; }
        }

        public ExpensesView (Model.Expense e)
        {
            ReferenceExpense = e;
        }
    }
}
