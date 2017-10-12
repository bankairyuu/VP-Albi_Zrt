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

namespace VP_Albi_Zrt_DESKTOP.Pages.CreatePages
{
    /// <summary>
    /// Interaction logic for CreateMonthlyFeePage.xaml
    /// </summary>
    public partial class CreateMonthlyFeePage : Page
    {
        public CreateMonthlyFeePage()
        {
            InitializeComponent();
            FeeType.ItemsSource = Enum.GetValues(typeof(Model.MonthlyFees.eFeeType)).Cast<Model.MonthlyFees.eFeeType>();
        }

        private void SaveButton_Click(object sender, RoutedEventArgs e)
        {

        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
            if (cuw != null)
            {
                cuw.Close();
            }
        }
    }
}
