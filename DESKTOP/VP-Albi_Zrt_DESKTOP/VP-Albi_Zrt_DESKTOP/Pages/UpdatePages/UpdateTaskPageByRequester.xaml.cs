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

        public UpdateTaskPageByRequester(Pages.Views.TasksView t)
        {
            // State 0==normal edit ; state==1 Accept the acceptance message ; state==2 Suggest an other acceptance message
            state = 0;

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

                }
            }
            else
            {

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
