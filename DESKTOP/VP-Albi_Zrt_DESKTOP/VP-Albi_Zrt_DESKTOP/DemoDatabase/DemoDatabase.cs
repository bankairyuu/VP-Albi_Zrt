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
            Model.Task Fürdőtakarítás = new Model.Task
            {
                ID = 2,
                From = Andi,
                TaskStatus = Model.Task.eStatus.Open,
                To = Roli,
                CreationDate = DateTime.Now.AddDays(-3),
                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                AcceptanceMessage = "",
                Description = "Haj van a fürdőkádban",
                RequestedCompletionDate = DateTime.Now.AddDays(5),
            };
            Model.Task Menedzsmentprogi = new Model.Task
            {
                ID = 3,
                From = Krisz,
                TaskStatus = Model.Task.eStatus.Open,
                To = Miki,
                CreationDate = DateTime.Now,
                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                AcceptanceMessage = "",
                Description = "Észrevettem egy hibát, ki kell javítani, majd dumáljuk meg, hogy mi lenne a legjobb megoldás",
                RequestedCompletionDate = DateTime.Now.AddDays(5),
            };

            Tasks.Add(Konyhatakarítás);
            Tasks.Add(Fürdőtakarítás);
            Tasks.Add(Menedzsmentprogi);
            #endregion

            #region MonthlyFees

            Model.MonthlyFees Víz = new Model.MonthlyFees
            {
                ID = 1,
                FeeType = Model.MonthlyFees.eFeeType.Water,
                Amount = 9570,
                CreationDate = DateTime.Now
            };
            Model.MonthlyFees Gáz = new Model.MonthlyFees
            {
                ID = 2,
                FeeType = Model.MonthlyFees.eFeeType.Gas,
                Amount = 10000,
                CreationDate = DateTime.Now
            };
            Model.MonthlyFees Net = new Model.MonthlyFees
            {
                ID = 3,
                FeeType = Model.MonthlyFees.eFeeType.Internet,
                Amount = 4600,
                CreationDate = DateTime.Now
            };
            Model.MonthlyFees Villany = new Model.MonthlyFees
            {
                ID = 4,
                FeeType = Model.MonthlyFees.eFeeType.Electricity,
                Amount = 12000,
                CreationDate = DateTime.Now
            };
            MonthlyFees.Add(Víz);
            MonthlyFees.Add(Gáz);
            MonthlyFees.Add(Net);
            MonthlyFees.Add(Villany);
            #endregion
        }

        public static bool AddUser(Model.User u)
        {
            foreach (Model.User user in Users)
            {
                if (u.ID < user.ID) u.ID++;
            }
            Users.Add(u);
            return true;
        }

        public static bool ExistingUser(string username)
        {
            foreach (Model.User u in Users)
            {
                if (u.Username == username)
                {
                    return true;
                }
            }
            return false;
        }
    }
}
