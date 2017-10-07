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

namespace VP_Albi_Zrt_DESKTOP.Pages
{
    /// <summary>
    /// Interaction logic for UsersPage.xaml
    /// </summary>
    public partial class UsersPage : Page
    {
        public UsersPage()
        {
            List<Pages.Views.UsersView> Users = new List<Views.UsersView>();

            foreach (Model.User user in DemoDatabase.DemoDatabase.Users) Users.Add(new Views.UsersView(user));

            InitializeComponent();

            this.UsersDataGrid.ItemsSource = Users;
        }
    }
}
