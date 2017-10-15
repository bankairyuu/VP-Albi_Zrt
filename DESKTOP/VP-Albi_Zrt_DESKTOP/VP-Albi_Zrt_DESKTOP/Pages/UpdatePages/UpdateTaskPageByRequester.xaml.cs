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

namespace VP_Albi_Zrt_DESKTOP.Pages.UpdatePages
{
    /// <summary>
    /// Interaction logic for UpdateTaskPageByRequester.xaml
    /// </summary>
    public partial class UpdateTaskPageByRequester : Page
    {
        List<string> Users = new List<string>();

        public UpdateTaskPageByRequester(Pages.Views.TasksView t)
        {
            foreach(Model.User user in DatabaseConnector.DatabaseConnector.Users)
            {
                Users.Add(user.Name);
            }
            InitializeComponent();
            this.From.Content = t.From;
            this.To.ItemsSource = Users;
            this.To.SelectedItem = t.To;
            this.Description.Text = t.Description;
            this.RequestedCompletitionDate.Text = t.RequestedCompletionDate.ToString();

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
