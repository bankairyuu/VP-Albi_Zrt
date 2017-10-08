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
using System.Windows.Shapes;

namespace VP_Albi_Zrt_DESKTOP.Windows
{
    /// <summary>
    /// Interaction logic for CUWindow.xaml -- Create and Update window
    /// </summary>
    public partial class CUWindow : Window
    {
        public enum ePage { Users, Tasks, Expense, MonthlyFee }
        public enum eMode { Create, Update }

        public CUWindow(ePage p, eMode m, object o = null)
        {
            InitializeComponent();

            if (m == eMode.Create)
            {
                switch (p)
                {
                    case ePage.Users:
                        this.Title = "Create User";
                        Pages.CreatePages.CreateUserPage userPage = new Pages.CreatePages.CreateUserPage();
                        this.Content = userPage;
                        this.Height = userPage.Height + 40;
                        this.Width = userPage.Width + 30;
                        this.ResizeMode = ResizeMode.NoResize;
                        break;
                    case ePage.Tasks:
                        this.Title = "Create Task";
                        Pages.CreatePages.CreateTaskPage taskPage = new Pages.CreatePages.CreateTaskPage();
                        this.Content = taskPage;
                        this.Height = taskPage.Height + 30;
                        this.Width = taskPage.Width + 30;
                        this.ResizeMode = ResizeMode.NoResize;
                        break;
                    case ePage.Expense:
                        this.Title = "Create Expense";
                        Pages.CreatePages.CreateExpensePage expensePage = new Pages.CreatePages.CreateExpensePage();
                        this.Content = expensePage;
                        this.Height = expensePage.Height + 30;
                        this.Width = expensePage.Width + 30;
                        this.ResizeMode = ResizeMode.NoResize;
                        break;
                    case ePage.MonthlyFee:
                        this.Title = "Create Monthly Fee";
                        Pages.CreatePages.CreateMonthlyFeePage monthlyFeePage = new Pages.CreatePages.CreateMonthlyFeePage();
                        this.Content = monthlyFeePage;
                        this.Height = monthlyFeePage.Height + 30;
                        this.Width = monthlyFeePage.Width + 30;
                        this.ResizeMode = ResizeMode.NoResize;
                        break;
                    default:
                        throw new Exception("Ilyet nem hozhatsz létre, mert nincs");
                }
            }
            else if (m == eMode.Update)
            {
                switch (p)
                {
                    case ePage.Users:
                        this.Title = "Edit User";
                        this.Content = new Pages.UpdatePages.UpdateUserPage((Pages.Views.UsersView)o);
                        break;
                    case ePage.Tasks:
                        this.Title = "Edit Task";
                        this.Content = new Pages.UpdatePages.UpdateTaskPage((Pages.Views.TasksView)o);
                        break;
                    case ePage.Expense:
                        this.Title = "Edit Expense";
                        this.Content = new Pages.UpdatePages.UpdateExpensePage((Pages.Views.ExpensesView)o);
                        break;
                    case ePage.MonthlyFee:
                        this.Title = "Edit Monthly Fee";
                        this.Content = new Pages.UpdatePages.UpdateMonthlyFeePage((Pages.Views.MonthlyFeesView)o);
                        break;
                    default:
                        throw new Exception("Ilyet nem modifikálhatsz, mert ilyen nincs");
                }
            }
            else
            {
                throw new Exception("Mi a fasz???");
            }

        }
    }
}
