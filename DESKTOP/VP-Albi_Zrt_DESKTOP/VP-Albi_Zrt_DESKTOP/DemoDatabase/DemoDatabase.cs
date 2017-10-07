using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.DemoDatabase
{
    public static class DemoDatabase
    {
        public static List<Model.User>          Users       = new List<Model.User>();
        public static List<Model.Expense>       Expenses    = new List<Model.Expense>();
        public static List<Model.Task>          Tasks       = new List<Model.Task>();
        public static List<Model.MonthlyFees>   MonthlyFees = new List<Model.MonthlyFees>();

        static DemoDatabase()
        {
            #region Users
            Model.User Miki = new Model.User
            {
                ID = 1,
                Name = "Herperger Miklós",
                Nickname = "Professzor úr",
                Username = "MikiTheProf",
                Password = "jelszo",
                Phone = "+36301234567",
                Email = "asdf@fdsa.com",
                CreditCardNumber = "123456789"
            };
            Model.User Krisz = new Model.User
            {
                ID = 2,
                Name = "Sipos Krisztián",
                Nickname = "Doktor úr",
                Username = "KriszTheDoctor",
                Password = "asdf123asdf",
                Phone = "+36309894774",
                Email = "asdf@fdsa.com",
                CreditCardNumber = "123456789"
            };
            Model.User Kapitány = new Model.User
            {
                ID = 3,
                Name = "Tüttő Lajos",
                Nickname = "Kapitány",
                Username = "LajosTheCaptain",
                Password = "lol",
                Phone = "+36301234567",
                Email = "asdf@fdsa.com",
                CreditCardNumber = "123456789"
            };
            Model.User Roli = new Model.User
            {
                ID = 4,
                Name = "Roli de Milyen",
                Nickname = "Roli",
                Username = "RoliTheAnchient",
                Password = "asdf123asdf",
                Phone = "+36308228258",
                Email = "asdf@fdsa.com",
                CreditCardNumber = "123456789"
            };
            Model.User Andi = new Model.User
            {
                ID = 5,
                Name = "Pesti Andrea Zita",
                Nickname = "Andi",
                Username = "AndiTheGirl",
                Password = "jelszo",
                Phone = "+36301234567",
                Email = "asdf@fdsa.com",
                CreditCardNumber = "123456789"
            };
            Users.Add(Miki);
            Users.Add(Krisz);
            Users.Add(Kapitány);
            Users.Add(Roli);
            Users.Add(Andi);

            #endregion

            #region Expenses
            Model.Expense Sör = new Model.Expense
            {
                ID = 1,
                Amount = 1200,
                ApartmentCost = false,
                CreationTime = DateTime.Now,
                Description = "esti sörözés",
                ExpenseMonth = DateTime.Now,
                From = Krisz,
                To = Miki,
                Name = "GarázsHétfő",
                Paid = true,
                SharingType = Model.Expense.eSharingType.Common
            };
            Model.Expense VízMiki = new Model.Expense
            {
                ID = 2,
                Amount = 2000,
                ApartmentCost = true,
                CreationTime = DateTime.Now,
                Description = "-",
                ExpenseMonth = DateTime.Now,
                From = Krisz,
                To = Miki,
                Name = "Havi víz / fő",
                Paid = false,
                SharingType = Model.Expense.eSharingType.Common
            };
            Model.Expense VízRoli = new Model.Expense
            {
                ID = 3,
                Amount = 2000,
                ApartmentCost = true,
                CreationTime = DateTime.Now,
                Description = "-",
                ExpenseMonth = DateTime.Now,
                From = Krisz,
                To = Roli,
                Name = "Havi víz / fő",
                Paid = false,
                SharingType = Model.Expense.eSharingType.Common
            };
            Model.Expense VízKapitány = new Model.Expense
            {
                ID = 4,
                Amount = 2000,
                ApartmentCost = true,
                CreationTime = DateTime.Now,
                Description = "-",
                ExpenseMonth = DateTime.Now,
                From = Krisz,
                To = Kapitány,
                Name = "Havi víz / fő",
                Paid = false,
                SharingType = Model.Expense.eSharingType.Common
            };
            Model.Expense VízAndi = new Model.Expense
            {
                ID = 5,
                Amount = 2000,
                ApartmentCost = true,
                CreationTime = DateTime.Now,
                Description = "-",
                ExpenseMonth = DateTime.Now,
                From = Krisz,
                To = Andi,
                Name = "Havi víz / fő",
                Paid = false,
                SharingType = Model.Expense.eSharingType.Common
            };
            Expenses.Add(Sör);
            Expenses.Add(VízAndi);
            Expenses.Add(VízKapitány);
            Expenses.Add(VízMiki);
            Expenses.Add(VízRoli);
            #endregion

            #region Tasks

            Model.Task Konyhatakarítás = new Model.Task
            {
                ID = 1,
                From = Andi,
                TaskStatus = Model.Task.eStatus.Open,
                To = Krisz,
                CreationDate = DateTime.Now.AddDays(-5),
                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                AcceptanceMessage = "",
                Description = "Légyszi mosogasd el a tányérokat!",
                RequestedCompletionDate = DateTime.Now.AddDays(3),
            };

            Tasks.Add(Konyhatakarítás);
            #endregion
        }
    }
}
