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
        List<Views.ExpensesView> Expenses = new List<Views.ExpensesView>();
        System.ComponentModel.BindingList<Views.ExpensesView> bindingList;

        public ExpensesPage()
        {
            foreach (Model.Expense expense in DatabaseConnector.DatabaseConnector.Expenses) Expenses.Add(new Views.ExpensesView(expense));
            bindingList = new System.ComponentModel.BindingList<Views.ExpensesView>(Expenses);
            InitializeComponent();

            this.ExpensesDataGrid.ItemsSource = bindingList;
        }

        private void Create_Click(object sender, RoutedEventArgs e)
        {
            new Windows.CUWindow(Windows.CUWindow.ePage.Expense, Windows.CUWindow.eMode.Create).Show();
        }

        private void Edit_Click(object sender, RoutedEventArgs e)
        {
            Pages.Views.ExpensesView expense = (Pages.Views.ExpensesView)ExpensesDataGrid.SelectedItem;
            new Windows.CUWindow(Windows.CUWindow.ePage.Expense, Windows.CUWindow.eMode.Update, expense).Show();
        }

        private void Delete_Click(object sender, RoutedEventArgs e)
        {
            Pages.Views.ExpensesView expense = (Pages.Views.ExpensesView)ExpensesDataGrid.SelectedItem;
            //DatabaseConnector.DatabaseConnector.DeleteExpense(expense);
            //bindingList.Remove(expense);
        }
    }
}
