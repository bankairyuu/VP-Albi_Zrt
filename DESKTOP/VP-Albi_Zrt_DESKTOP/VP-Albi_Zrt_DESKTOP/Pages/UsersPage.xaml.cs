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
    /// Interaction logic for UsersPage.xaml
    /// </summary>
    public partial class UsersPage : Page
    {
        Windows.CUWindow cuw;

        public UsersPage()
        {
            List<Views.UsersView> Users = new List<Views.UsersView>();

            foreach (Model.User user in DatabaseConnector.DatabaseConnector.Users) Users.Add(new Views.UsersView(user));

            InitializeComponent();

            this.UsersDataGrid.ItemsSource = Users;
        }

        private void Create_Click(object sender, RoutedEventArgs e)
        {
            cuw = new Windows.CUWindow(Windows.CUWindow.ePage.Users, Windows.CUWindow.eMode.Create);
            cuw.Show();
        }

        private void Edit_Click(object sender, RoutedEventArgs e)
        {
            Views.UsersView user = (Pages.Views.UsersView)UsersDataGrid.SelectedItem;
            if (user != null)
            {
                cuw = new Windows.CUWindow(Windows.CUWindow.ePage.Users, Windows.CUWindow.eMode.Update, user);
                cuw.Show();
            }
            else
            {
                MessageBox.Show("Select an item!");
            }
        }

        private void Delete_Click(object sender, RoutedEventArgs e)
        {
            Views.UsersView user = (Pages.Views.UsersView)UsersDataGrid.SelectedItem;
            DatabaseConnector.DatabaseConnector.DeleteUser(user);
        }
    }
}
