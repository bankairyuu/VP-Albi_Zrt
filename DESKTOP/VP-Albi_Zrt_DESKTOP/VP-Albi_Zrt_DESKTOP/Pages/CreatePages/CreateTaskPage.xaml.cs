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
        List<string> Users = new List<string>();
        List<string> Usernames = new List<string>();

        public CreateTaskPage()
        {
            InitializeComponent();
            foreach (Model.User u in DatabaseConnector.DatabaseConnector.Users) {
                Users.Add(u.Name);
                Usernames.Add(u.Username);
            }
            From.ItemsSource = Users;
            for (int i=0; i<Users.Count; i++)
            {
                CheckBox cb = new CheckBox();
                cb.Name = Usernames[i];
                cb.Content = Users[i];
                ToStackPanel.Children.Add(cb);
                this.Height += 15;
                ToGridRow.Height = new GridLength(ToGridRow.Height.Value + 15);
            }
        }

        private void SaveButton_Click(object sender, RoutedEventArgs e)
        {
            if (
                   From.SelectedItem != null
                && !string.IsNullOrEmpty(Description.Text)
                && !string.IsNullOrWhiteSpace(Description.Text)

                && Logic.PatternCheck.PatternCheck.CheckToPattern(Logic.PatternCheck.PatternCheck.ePatterns.Date, RequestedCompletitionDate.SelectedDate.ToString())
               )
            {
                List<string> assignedUsers = new List<string>();
                foreach (CheckBox cb in ToStackPanel.Children)
                {
                    if (cb.IsChecked == true)
                    {
                        assignedUsers.Add(cb.Name);
                    }
                }
                if (assignedUsers.Count != 0)
                {
                    Model.User selectedFromUser = null;
                    List<Model.User> AssignedUsers = new List<Model.User>();

                    try
                    {
                        selectedFromUser = DatabaseConnector.DatabaseConnector.SearchUser(Usernames[From.SelectedIndex]);
                        foreach (string assignedUser in assignedUsers)
                        {
                            AssignedUsers.Add(DatabaseConnector.DatabaseConnector.SearchUser(assignedUser));
                        }
                    }
                    catch
                    {
                        MessageBox.Show("The selected user didn't exist anymore, please refresh the Task page!");
                    }

                    if (selectedFromUser != null)
                    {
                        foreach (Model.User assignedTo in AssignedUsers)
                        {
                            Model.Task newTask = new Model.Task
                            {
                                ID = -1,
                                CreationDate = DateTime.Now,
                                Description = Description.Text,
                                From = selectedFromUser,
                                To = assignedTo,
                                RequestedCompletionDate = Convert.ToDateTime(RequestedCompletitionDate.SelectedDate),
                                TaskStatus = Model.Task.eStatus.Open,
                                AcceptanceProperty = Model.Task.eAcceptanceProperty.Waiting_for_reply,
                                AcceptanceMessage = "",
                                PlannedCompletionDate = Convert.ToDateTime(RequestedCompletitionDate.SelectedDate)
                            };
                            DatabaseConnector.DatabaseConnector.AddTask(newTask);
                        }
                        MessageBox.Show("Task(s) Created");
                        Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                        if (cuw != null)
                        {
                            cuw.Close();
                        }
                    }
                }
                else
                {
                    MessageBox.Show("Assign users to the Task!");
                }
            }
            else
            {
                MessageBox.Show("You didn't added all of the required datas " +
                    "or the added date is not valid!\n" +
                    "The requested date need to be later, than the creation date!");
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
