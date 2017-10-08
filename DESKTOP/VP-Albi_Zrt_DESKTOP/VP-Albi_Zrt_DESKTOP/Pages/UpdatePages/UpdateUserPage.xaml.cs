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

        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
