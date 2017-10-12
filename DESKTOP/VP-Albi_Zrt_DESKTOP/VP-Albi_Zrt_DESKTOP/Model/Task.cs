using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Model
{
    public class Task
    {
        public enum eStatus { Open, Acceptence, In_work, Done };
        public enum eAcceptanceProperty { Accepted, Accepted_with_conditions, Denied, Waiting_for_reply };

        public int ID { get; set; }
        public DateTime CreationDate { get; set; }
        public User From { get; set; }
        public User To { get; set; }
        public string Description { get; set; }
        public eStatus TaskStatus { get; set; }
        public DateTime RequestedCompletionDate { get; set; }
        public DateTime PlannedCompletionDate { get; set; }
        public eAcceptanceProperty AcceptanceProperty { get; set; }
        public string AcceptanceMessage { get; set; }

        
        public Task() { }
    }
}
