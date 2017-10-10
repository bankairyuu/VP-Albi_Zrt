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
    /// Interaction logic for UpdateUserPage.xaml
    /// </summary>
    public partial class UpdateUserPage : Page
    {
        public UpdateUserPage(Pages.Views.UsersView u)
        {
            InitializeComponent();
            this.Username.Text = u.Username;
            this.Phone.Text = u.Phone;
            this.Password.Password = u.Password;
            this.Email.Text = u.Email;
            this.Name.Text = u.Name;
            this.Nickname.Text = u.Nickname;
            this.CreditCardNumber.Text = u.CreditCardNumber;
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
                Model.User user = DatabaseConnector.DatabaseConnector.SearchUser(Username.Text);

                user.Phone = Phone.Text;
                user.Password = Password.Password;
                user.Email = Email.Text;
                user.Name = Name.Text;
                user.Nickname = Nickname.Text;
                user.CreditCardNumber = CreditCardNumber.Text;

                DatabaseConnector.DatabaseConnector.UpdateUser(user);
                
                MessageBox.Show("User Updated");
                Windows.CUWindow cuw = Application.Current.MainWindow as Windows.CUWindow;
                if (cuw != null)
                {
                    cuw.Close();
                }
            }
            else
            {
                MessageBox.Show("Update went fail");
            }
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
