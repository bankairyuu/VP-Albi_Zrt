using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace VP_Albi_Zrt_DESKTOP.Pages
{
    /// <summary>
    /// Interaction logic for ExpensesPage.xaml
    /// </summary>
    public partial class ExpensesPage : Page
    {
        public ExpensesPage()
        {
            List<Model.Expense> DemoExpenses = new List<Model.Expense>();

            Model.User Krisz = new Model.User()
                {
                    ID = 2,
                    Name = "Sipos István Krisztián",
                    Nickname = "Krisz",
                    Username = "bankairyuu",
                    Password = "sárgarigófészek",
                    CreditCardNumber = "123123123",
                    Email = "csiga@láb.com",
                    Phone = "+36309894774"
                };
            Model.User Miki = new Model.User()
                {
                    ID = 1,
                    Name = "Herperger Miklós",
                    Nickname = "Professzor úr",
                    Username = "hmiki",
                    Password = "MikiAKirály",
                    CreditCardNumber = "123123123",
                    Email = "hmiki@a_nagy.com",
                    Phone = "+36301234567"
                };

            DemoExpenses.Add(new Model.Expense()
            {
                ID = 1,
                From = Miki,
                To = Krisz,
                Name = "SÖR",
                Description = "INNI KELL NEM ÉRTED??",
                SharingType = Model.Expense.eSharingType.Common,
                Paid = false,
                ExpenseMonth = DateTime.Now.AddDays(7),
                CreationTime = DateTime.Now,
                ApartmentCost = false,
                Amount = 1200
            });

            DemoExpenses.Add(new Model.Expense()
            {
                ID = 2,
                From = Krisz,
                To = Miki,
                Name = "SÖR",
                Description = "INNI KELL NEM ÉRTED??",
                SharingType = Model.Expense.eSharingType.Common,
                Paid = false,
                ExpenseMonth = DateTime.Now.AddDays(7),
                CreationTime = DateTime.Now,
                ApartmentCost = false,
                Amount = 1200
            });

            InitializeComponent();

            this.ExpensesDataGrid.ItemsSource = DemoExpenses;
        }
    }
}
