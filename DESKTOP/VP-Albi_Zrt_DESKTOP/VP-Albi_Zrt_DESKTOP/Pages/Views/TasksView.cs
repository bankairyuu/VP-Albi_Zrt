using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Pages.Views
{
    class TasksView
    {
        Model.Task ReferenceTask;

        public int ID
        {
            get { return ReferenceTask.ID; }
            set { }
        }
        public DateTime CreationDate
        {
            get { return ReferenceTask.CreationDate; }
            set { }
        }
        public string From
        {
            get { return ReferenceTask.From.Name; }
            set { }
        }
        public string To
        {
            get { return ReferenceTask.To.Name; }
            set { }
        }
        public string Description
        {
            get { return ReferenceTask.Description; }
            set { ReferenceTask.Description = value; }
        }
        public Model.Task.eStatus TaskStatus
        {
            get { return ReferenceTask.TaskStatus; }
            set { ReferenceTask.TaskStatus = value; }
        }
        public DateTime RequestedCompletionDate
        {
            get { return ReferenceTask.RequestedCompletionDate; }
            set { ReferenceTask.RequestedCompletionDate = value; }
        }
        public DateTime PlannedCompletionDate
        {
            get { return ReferenceTask.PlannedCompletionDate; }
            set { ReferenceTask.PlannedCompletionDate = value; }
        }
        public Model.Task.eAcceptanceProperty AcceptanceProperty
        {
            get { return ReferenceTask.AcceptanceProperty; }
            set { ReferenceTask.AcceptanceProperty = value; }
        }
        public string AcceptanceMessage
        {
            get { return ReferenceTask.AcceptanceMessage; }
            set { ReferenceTask.AcceptanceMessage = value; }
        }

        public TasksView(Model.Task t)
        {
            ReferenceTask = t;
        }
    }
}
