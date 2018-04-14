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
        int state;
        Pages.Views.TasksView refTask;

        public UpdateTaskPageByRequester(Pages.Views.TasksView t)
        {
            // State 0==normal edit ; state==1 Accept the acceptance message ; state==2 Suggest an other acceptance message
            state = 0;
            refTask = t;

            foreach(Model.User user in DatabaseConnector.DatabaseConnector.Users)
            {
                Users.Add(user.Name);
            }
            InitializeComponent();
            this.From.Content = t.From;
            this.To.ItemsSource = Users;
            this.To.SelectedItem = t.To;
            this.Description.Text = t.Description;

            // Amíg még nem fogadta el, addig szabadon szerkeszthetem a dátumot
            if (t.AcceptanceProperty == Model.Task.eAcceptanceProperty.Waiting_for_reply)
            {
                state = 0;
                DateSection.Height = new GridLength(30);
                AcceptanceSection.Height = new GridLength(0);
                this.RequestedCompletitionDate.Text = t.RequestedCompletionDate.ToString();
            }
            else
            {
                state = 1;
                AcceptanceSection.Height = new GridLength(30);
                DateSection.Height = new GridLength(0);
                AcceptanceCriteria.Text = t.AcceptanceMessage;
                SaveButton.Content = "Accept";
            }
        }

        //TODO: leprogramozni azt, hogy megváltozik az Acceptance message
        //TODO: leprogramozni azt, hogy megváltozik az assigned user

        private void SaveButton_Click(object sender, RoutedEventArgs e)
        {
            if (state == 0)
            {
                if (    To.SelectedItem != null
                    &&  !string.IsNullOrEmpty(Description.Text)
                    &&  !string.IsNullOrWhiteSpace(Description.Text)

                    && Logic.PatternCheck.PatternCheck.CheckToPattern(Logic.PatternCheck.PatternCheck.ePatterns.Date, RequestedCompletitionDate.SelectedDate.ToString())
                   )
                {
                    DatabaseConnector.DatabaseConnector.UpdateTask(refTask.ID, Model.Task.eAcceptanceProperty.Waiting_for_reply, "", Model.Task.eStatus.Open);
                }
            }
            else if (state == 1)
            {
                if (To.SelectedItem != null
                    && !string.IsNullOrEmpty(Description.Text)
                    && !string.IsNullOrWhiteSpace(Description.Text)
                   )
                {
                    DatabaseConnector.DatabaseConnector.UpdateTask(refTask.ID, Model.Task.eAcceptanceProperty.Accepted, refTask.AcceptanceMessage, Model.Task.eStatus.In_work);
                    Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                    if (cuw != null)
                    {
                        cuw.Close();
                    }
                }
            }
            else if (state == 2)
            {
                // Megváltozik az assigned user

                // Megváltozik az acceptance message
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
    }
}
