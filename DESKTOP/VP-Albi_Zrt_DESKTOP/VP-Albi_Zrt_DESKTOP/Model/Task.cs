using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    class Task
    {
        public enum eStatus { Open, Under_construction, In_work, Done };
        public enum eAcceptanceProperty { Accepted, Accepted_with_conditions, Denied };

        int _ID;
        DateTime _CreationDate;
        User _From;
        User _To;
        string _Description;
        eStatus _TaskStatus;
        DateTime _RequestedCompletionDate;
        DateTime _PlannedCompletionDate;
        eAcceptanceProperty _AcceptanceProperty;
        string _AcceptanceMessage;

        public int ID { get => _ID; set => _ID = value; }
        public DateTime CreationDate { get => _CreationDate; set => _CreationDate = value; }
        public User From { get => _From; set => _From = value; }
        public User To { get => _To; set => _To = value; }
        public string Description { get => _Description; set => _Description = value; }
        public eStatus TaskStatus { get => _TaskStatus; set => _TaskStatus = value; }
        public DateTime RequestedCompletionDate { get => _RequestedCompletionDate; set => _RequestedCompletionDate = value; }
        public DateTime PlannedCompletionDate { get => _PlannedCompletionDate; set => _PlannedCompletionDate = value; }
        public eAcceptanceProperty AcceptanceProperty { get => _AcceptanceProperty; set => _AcceptanceProperty = value; }
        public string AcceptanceMessage { get => _AcceptanceMessage; set => _AcceptanceMessage = value; }

        public Task(int ID, DateTime CreationDate, User From, User To, string Description, eStatus TaskStatus, DateTime RequestedCompletionDate, DateTime PlannedCompletionDate, eAcceptanceProperty AcceptanceProperty, string AcceptanceMessage)
        {
            this.ID = ID;
            this.CreationDate = CreationDate;
            this.From = From;
            this.To = To;
            this.Description = Description;
            this.TaskStatus = TaskStatus;
            this.RequestedCompletionDate = RequestedCompletionDate;
            this.PlannedCompletionDate = PlannedCompletionDate;
            this.AcceptanceProperty = AcceptanceProperty;
            this.AcceptanceMessage = AcceptanceMessage;
        }

        public Task() { }
    }
}
