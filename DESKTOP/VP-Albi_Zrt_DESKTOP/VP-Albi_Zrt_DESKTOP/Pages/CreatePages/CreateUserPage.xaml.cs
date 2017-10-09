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
    /// Interaction logic for CreateUserPage.xaml
    /// </summary>
    public partial class CreateUserPage : Page
    {
        public CreateUserPage()
        {
            InitializeComponent();
        }

        private void SaveButton_Click(object sender, RoutedEventArgs e)
        {
            if (
                    !string.IsNullOrWhiteSpace(Name.Text)
                &&  !string.IsNullOrEmpty(Name.Text)
                &&  !string.IsNullOrWhiteSpace(Username.Text)
                &&  !string.IsNullOrEmpty(Username.Text)
                &&  !string.IsNullOrWhiteSpace(Password.Password)
                &&  !string.IsNullOrEmpty(Password.Password)
                &&  !string.IsNullOrWhiteSpace(Phone.Text)
                &&  !string.IsNullOrEmpty(Phone.Text)
                &&  !string.IsNullOrWhiteSpace(CreditCardNumber.Text)
                &&  !string.IsNullOrEmpty(CreditCardNumber.Text)
                &&  !string.IsNullOrWhiteSpace(Nickname.Text)
                &&  !string.IsNullOrEmpty(Nickname.Text)
                &&  !string.IsNullOrWhiteSpace(Email.Text)
                &&  !string.IsNullOrEmpty(Email.Text)

                &&  Logic.PatternCheck.PatternCheck.CheckToPattern(Logic.PatternCheck.PatternCheck.ePatterns.Email, Email.Text)
                &&  Logic.PatternCheck.PatternCheck.CheckToPattern(Logic.PatternCheck.PatternCheck.ePatterns.Phone, Phone.Text)
                &&  Logic.PatternCheck.PatternCheck.CheckToPattern(Logic.PatternCheck.PatternCheck.ePatterns.CreditCardNumber, CreditCardNumber.Text)
               )
            {
                Model.User newUser = new Model.User
                {
                    ID = -1,
                    Email = Email.Text,
                    Name = Name.Text,
                    Username = Username.Text,
                    Password = Password.Password,
                    CreditCardNumber = CreditCardNumber.Text,
                    Nickname = Nickname.Text,
                    Phone = Phone.Text
                };

                if (    !DemoDatabase.DemoDatabase.ExistingUser(newUser.Username)
                    &&  DemoDatabase.DemoDatabase.AddUser(newUser)
                   )
                {
                    MessageBox.Show("User Created");
                    Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                    if (cuw != null)
                    {
                        cuw.Close();
                    }
                }
            }
            else
            {
                    MessageBox.Show("This Username is occupied, or the datas are not satisfy the patterns");
            }
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
