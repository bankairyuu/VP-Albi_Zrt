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
    /// Interaction logic for TasksPage.xaml
    /// </summary>
    public partial class TasksPage : Page
    {
        List<Pages.Views.TasksView> Tasks = new List<Views.TasksView>();

        public TasksPage()
        {
            foreach (Model.Task task in DatabaseConnector.DatabaseConnector.Tasks) Tasks.Add(new Views.TasksView(task));

            InitializeComponent();

            this.TasksDataGrid.ItemsSource = Tasks;
        }

        private void Create_Click(object sender, RoutedEventArgs e)
        {
            new Windows.CUWindow(Windows.CUWindow.ePage.Tasks, Windows.CUWindow.eMode.Create).Show();
        }

        private void Edit_Click(object sender, RoutedEventArgs e)
        {
            Pages.Views.TasksView task = (Pages.Views.TasksView)TasksDataGrid.SelectedItem;
            new Windows.CUWindow(Windows.CUWindow.ePage.Tasks, Windows.CUWindow.eMode.Update, task).Show();
        }

        private void Delete_Click(object sender, RoutedEventArgs e)
        {
            Pages.Views.TasksView task = (Pages.Views.TasksView)TasksDataGrid.SelectedItem;
        }
    }
}
