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
    /// Interaction logic for FeesPage.xaml
    /// </summary>
    public partial class FeesPage : Page
    {
        List<Pages.Views.MonthlyFeesView> MonthlyFees = new List<Views.MonthlyFeesView>();

        public FeesPage()
        {
            foreach (Model.MonthlyFees fee in DemoDatabase.DemoDatabase.MonthlyFees) MonthlyFees.Add(new Views.MonthlyFeesView(fee));

            InitializeComponent();

            this.FeesDataGrid.ItemsSource = MonthlyFees;
        }

        private void Create_Click(object sender, RoutedEventArgs e)
        {
            new Windows.CUWindow(Windows.CUWindow.ePage.MonthlyFee, Windows.CUWindow.eMode.Create).Show();
        }

        private void Edit_Click(object sender, RoutedEventArgs e)
        {
            Pages.Views.MonthlyFeesView monthlyfee = (Pages.Views.MonthlyFeesView)FeesDataGrid.SelectedItem;
            new Windows.CUWindow(Windows.CUWindow.ePage.MonthlyFee, Windows.CUWindow.eMode.Update, monthlyfee).Show();
        }

        private void Delete_Click(object sender, RoutedEventArgs e)
        {
            Pages.Views.MonthlyFeesView monthlyfee = (Pages.Views.MonthlyFeesView)FeesDataGrid.SelectedItem;
        }
    }
}
