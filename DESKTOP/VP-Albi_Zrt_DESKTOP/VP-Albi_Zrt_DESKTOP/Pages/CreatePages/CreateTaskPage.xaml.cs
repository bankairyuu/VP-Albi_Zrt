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
    /// Interaction logic for CreateTaskPage.xaml
    /// </summary>
    public partial class CreateTaskPage : Page
    {
        public CreateTaskPage()
        {
            InitializeComponent();
            List<string> Users = new List<string>();
            foreach (Model.User u in DemoDatabase.DemoDatabase.Users) Users.Add(u.Name);
            From.ItemsSource = Users;
            To.ItemsSource = Users;
        }

        private void SaveButton_Click(object sender, RoutedEventArgs e)
        {

        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
