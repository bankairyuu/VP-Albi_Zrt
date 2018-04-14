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
        Windows.CUWindow cuw;
        List<Views.TasksView> Tasks = new List<Views.TasksView>();
        List<Views.TasksView> WFAtasks = new List<Views.TasksView>();
        System.ComponentModel.BindingList<Views.TasksView> bindingList_Tasks;
        System.ComponentModel.BindingList<Views.TasksView> bindingList_WFAtasks;

        public TasksPage()
        {
            foreach (Model.Task task in DatabaseConnector.DatabaseConnector.Tasks) Tasks.Add(new Views.TasksView(task));
            foreach (Model.Task task in DatabaseConnector.DatabaseConnector.Tasks)
            {
                if (
                     (      task.AcceptanceProperty == Model.Task.eAcceptanceProperty.Accepted_with_conditions
                        ||  task.AcceptanceProperty == Model.Task.eAcceptanceProperty.Denied
                     )
                     && task.From.Username == Logic.PermissionHandling.LoginHandler.LoggedInUserName
                   )
                {
                    WFAtasks.Add(new Views.TasksView(task));
                }
            }

            bindingList_Tasks = new System.ComponentModel.BindingList<Views.TasksView>(Tasks);
            bindingList_WFAtasks = new System.ComponentModel.BindingList<Views.TasksView>(WFAtasks);

            InitializeComponent();

            this.TasksDataGrid.ItemsSource = bindingList_Tasks;
            this.WaitingForAcceptance.ItemsSource = bindingList_WFAtasks;
        }

        private void Create_Click(object sender, RoutedEventArgs e)
        {
            cuw = new Windows.CUWindow(Windows.CUWindow.ePage.Tasks, Windows.CUWindow.eMode.Create);
            cuw.Show();
        }

        private void Edit_Click(object sender, RoutedEventArgs e)
        {
            Views.TasksView task = (Views.TasksView)TasksDataGrid.SelectedItem;
            Views.TasksView WFAtask = (Views.TasksView)WaitingForAcceptance.SelectedItem;
            if (task != null)
            {
                new Windows.CUWindow(Windows.CUWindow.ePage.Tasks, Windows.CUWindow.eMode.Update, task).Show();
            }
            else if (WFAtask != null)
            {
                new Windows.CUWindow(Windows.CUWindow.ePage.Tasks, Windows.CUWindow.eMode.Update, WFAtask).Show();
            }
            else
            {
                MessageBox.Show("Select an item!");
            }
        }

        private void Delete_Click(object sender, RoutedEventArgs e)
        {
            Views.TasksView task = (Views.TasksView)TasksDataGrid.SelectedItem;
            DatabaseConnector.DatabaseConnector.DeleteTask(task);


        }
    }
}
