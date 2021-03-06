﻿using System;
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
using VP_Albi_Zrt_DESKTOP.Logic.PermissionHandling;

namespace VP_Albi_Zrt_DESKTOP
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            try
            {
                DatabaseConnector.DatabaseConnector.ConnectAndSync();
            }
            catch (Exception exc)
            {
                MessageBox.Show("Connection failed:\n" + exc.Message);
            }
        }

        private void Login()
        {

            if (LoginHandler.Login(usernameTextBox.Text, passwordTextBox.Password))
            {
                // Be tudtunk jelentkezni, indulhat a móka
                VP_Albi_Zrt_DESKTOP.Windows.VP_Albi_Zrt_Main Main = new Windows.VP_Albi_Zrt_Main();
                Main.Show();
                this.Close();
            }
            else
            {
                MessageBox.Show("Wrong username/password");
            }
        }

        private void LoginButton_Click(object sender, RoutedEventArgs e)
        {
                Login();
        }

        private void passwordTextBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Return)
            {
                Login();
            }
        }
    }
}
