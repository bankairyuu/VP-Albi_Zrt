using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace VP_Albi_Zrt_DESKTOP.DatabaseConnector
{
    public static class DatabaseConnector
    {
        public static List<Model.User>           Users          = new List<Model.User>();
        public static List<Model.Expense>        Expenses       = new List<Model.Expense>();
        public static List<Model.Task>           Tasks          = new List<Model.Task>();
        public static List<Model.MonthlyFees>    MonthlyFees    = new List<Model.MonthlyFees>();
        public static Dictionary<string, string> UserDictionary = new Dictionary<string, string>();

        static DatabaseConnector()
        {
            foreach (Model.User user in Users)
            {
                UserDictionary.Add(user.Name, user.Username);
            }
        }

        public static void ConnectAndSync()
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
            Model.User asdf = new Model.User
            {
                ID = 6,
                Name = "asdf",
                Nickname = "asdf",
                Username = "asdf",
                Password = "asdf",
                Phone = "+36-30-123-4567",
                Email = "asdf@fdsa.com",
                CreditCardNumber = "11773470-00551962-00000000"
            };
            Users.Add(Miki);
            Users.Add(Krisz);
            Users.Add(Kapitány);
            Users.Add(Roli);
            Users.Add(Andi);
            Users.Add(asdf);
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
            Model.Task AsdfAd = new Model.Task
            {
                ID = 4,
                From = asdf,
                TaskStatus = Model.Task.eStatus.Open,
                To = Krisz,
                CreationDate = DateTime.Now,
                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                AcceptanceMessage = "",
                Description = "Asdf ad feladat",
                RequestedCompletionDate = DateTime.Now.AddDays(5),
            };
            Model.Task AsdfKap = new Model.Task
            {
                ID = 5,
                From = Krisz,
                TaskStatus = Model.Task.eStatus.Open,
                To = asdf,
                CreationDate = DateTime.Now,
                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                AcceptanceMessage = "",
                Description = "Asdf kap feladat",
                RequestedCompletionDate = DateTime.Now.AddDays(5),
            };
            Model.Task AsdfAdKap = new Model.Task
            {
                ID = 6,
                From = asdf,
                TaskStatus = Model.Task.eStatus.Open,
                To = asdf,
                CreationDate = DateTime.Now,
                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                AcceptanceMessage = "",
                Description = "Asdf ad magának feladat",
                RequestedCompletionDate = DateTime.Now.AddDays(5),
            };
            Tasks.Add(Konyhatakarítás);
            Tasks.Add(Fürdőtakarítás);
            Tasks.Add(Menedzsmentprogi);
            Tasks.Add(AsdfAd);
            Tasks.Add(AsdfKap);
            Tasks.Add(AsdfAdKap);
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

        #region User handling operations

        public static bool AddUser(Model.User u)
        {
            foreach (Model.User user in Users)
            {
                if (u.ID < user.ID) u.ID=user.ID;
                if (u.ID == user.ID) u.ID++;
            }
            Users.Add(u);
            return true;
        }

        public static bool UpdateUser(string username, string phone, string password, string email, string name, string nickname, string creditcardnumber)
        {
            Model.User user = SearchUser(username);

            if (user != null)
            {
                user.Phone = phone;
                user.Password = password;
                user.Email = email;
                user.Name = name;
                user.Nickname = nickname;
                user.CreditCardNumber = creditcardnumber;
                return true;
            }
            
            return false;
        }

        public static void DeleteUser(Pages.Views.UsersView u)
        {
            Model.User user = SearchUser(u.Username);
            Users.Remove(user);
        }

        public static Model.User SearchUser(string username)
        {
            foreach(Model.User u in Users)
            {
                if (u.Username == username) return u;
            }
            return null;
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

        #endregion

        #region Expense handling

        #endregion

        #region Task Handling

        public static bool AddTask(Model.Task t)
        {
            foreach (Model.Task task in Tasks)
            {
                if (t.ID < task.ID) t.ID = task.ID;
                if (t.ID == task.ID) t.ID++;
            }
            Tasks.Add(t);
            return true;
        }

        /// <summary>
        /// Update task with acceptance/acceptance with message/decline with message, but not reassigne
        /// </summary>
        /// <param name="id"></param>
        /// <param name="acceptanceProperty"></param>
        /// <param name="acceptanceMessage"></param>
        /// <param name="taskStatus"></param>
        /// <returns></returns>
        public static bool UpdateTask(int id, Model.Task.eAcceptanceProperty acceptanceProperty, string acceptanceMessage, Model.Task.eStatus taskStatus)
        {
            Model.Task task = SearchTask(id);

            if (task != null)
            {
                task.AcceptanceProperty = acceptanceProperty;
                task.AcceptanceMessage = acceptanceMessage;
                task.TaskStatus = taskStatus;

                return true;
            }

            return false;
        }

        public static Model.Task SearchTask(int id)
        {
            foreach (Model.Task t in Tasks)
            {
                if (t.ID == id) return t;
            }
            return null;
        }

        public static void DeleteTask(Pages.Views.TasksView t)
        {
            Model.Task task = SearchTask(t.ID);
            Tasks.Remove(task);
        }

        #endregion

        #region Monthly Fees Handling

        #endregion
    }
}
