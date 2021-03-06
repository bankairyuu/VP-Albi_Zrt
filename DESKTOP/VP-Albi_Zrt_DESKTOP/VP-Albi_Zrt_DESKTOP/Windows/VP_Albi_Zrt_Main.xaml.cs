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
using System.Windows.Shapes;
using VP_Albi_Zrt_DESKTOP.Pages;
using VP_Albi_Zrt_DESKTOP;

namespace VP_Albi_Zrt_DESKTOP.Windows
{
    /// <summary>
    /// Interaction logic for VP_Albi_Zrt_Main.xaml
    /// </summary>
    public partial class VP_Albi_Zrt_Main : Window
    {
        public VP_Albi_Zrt_Main()
        {
            InitializeComponent();
            this.Title += " :: " + Logic.PermissionHandling.LoginHandler.LoggedInUserName;
            ActivePage.NavigationUIVisibility = System.Windows.Navigation.NavigationUIVisibility.Hidden;
            ActivePage.Content = new DefaultPage();
        }

        public void UsersButton_Click(object sender, RoutedEventArgs e)
        {
            ActivePage.Content = new UsersPage();
            this.Title = "VP-Albi Zrt. :: User datas :: " + Logic.PermissionHandling.LoginHandler.LoggedInUserName;
        }

        public void TasksButton_Click(object sender, RoutedEventArgs e)
        {
            ActivePage.Content = new TasksPage();
            this.Title = "VP-Albi Zrt. :: Task datas :: " + Logic.PermissionHandling.LoginHandler.LoggedInUserName;
        }

        public void ExpansesButton_Click(object sender, RoutedEventArgs e)
        {
            ActivePage.Content = new ExpensesPage();
            this.Title = "VP-Albi Zrt. :: Expense datas :: " + Logic.PermissionHandling.LoginHandler.LoggedInUserName;
        }

        public void FeesButton_Click(object sender, RoutedEventArgs e)
        {
            ActivePage.Content = new FeesPage();
            this.Title = "VP-Albi Zrt. :: Fee datas :: " + Logic.PermissionHandling.LoginHandler.LoggedInUserName;
        }
    }
}
