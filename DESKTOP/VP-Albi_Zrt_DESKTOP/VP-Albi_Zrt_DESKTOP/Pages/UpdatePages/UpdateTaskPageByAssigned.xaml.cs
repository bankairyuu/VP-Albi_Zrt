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
    /// Interaction logic for UpdateTaskPageByAssigned.xaml
    /// </summary>
    public partial class UpdateTaskPageByAssigned : Page
    {
        List<string> Users = new List<string>();
        private bool criteriaContext = false;
        Views.TasksView tv;

        public UpdateTaskPageByAssigned(Pages.Views.TasksView t)
        {
            tv = t;
            foreach (Model.User user in DatabaseConnector.DatabaseConnector.Users)
            {
                Users.Add(user.Name);
            }
            InitializeComponent();
            this.From.Content = t.From;
            this.To.Content = t.To;
            this.Description.Text = t.Description;
            this.Acceptance.ItemsSource = Enum.GetValues(typeof(Model.Task.eAcceptanceProperty)).Cast<Model.Task.eAcceptanceProperty>();
            this.Acceptance.SelectedItem = t.AcceptanceProperty;
        }



        private void SaveButton_Click(object sender, RoutedEventArgs e)
        {
            if (this.Acceptance.SelectedIndex != 3)
            {
                if (this.Acceptance.SelectedIndex == 1 || this.Acceptance.SelectedIndex == 2)
                {
                    if (!string.IsNullOrEmpty(this.Criteria.Text) && !string.IsNullOrWhiteSpace(this.Criteria.Text))
                    {
                        DatabaseConnector.DatabaseConnector.UpdateTask(tv.ID, (Model.Task.eAcceptanceProperty)Acceptance.SelectedItem, Criteria.Text, Model.Task.eStatus.Acceptence);
                    }
                }
                else
                {
                    DatabaseConnector.DatabaseConnector.UpdateTask(tv.ID, (Model.Task.eAcceptanceProperty)Acceptance.SelectedItem, "", Model.Task.eStatus.In_work);
                }
                MessageBox.Show("User Updated");
                Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                if (cuw != null)
                {
                    cuw.Close();
                }
            }
            else
            {
                MessageBox.Show("You have to accept or denie the task!\n" +
                    "If you want to do nothing, click on Cancel");
            }
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
            if (cuw != null)
            {
                cuw.Close();
            }
        }

        private void Acceptance_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (
                  !criteriaContext && (Acceptance.SelectedIndex == 1 || Acceptance.SelectedIndex == 2)
               )
            {
                criteriaContext = true;
                this.Height += 60;
                GridCriteriaRow.Height = new GridLength(50);
                Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                if (cuw != null)
                {
                    cuw.Height = this.Height + 30;
                }
            }
            else if (criteriaContext && (Acceptance.SelectedIndex == 0 || Acceptance.SelectedIndex == 3))
            {
                criteriaContext = false;
                this.Height -= 60;
                GridCriteriaRow.Height = new GridLength(0);
                Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                if (cuw != null)
                {
                    cuw.Height = this.Height + 30;
                }
            }
        }
    }
}
