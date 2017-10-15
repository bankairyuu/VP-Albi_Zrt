using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VP_Albi_Zrt_DESKTOP.Logic.PatternCheck
{
    public static class PatternCheck
    {
        public enum ePatterns { Email, Phone, CreditCardNumber, Date }

        public static bool CheckToPattern(ePatterns p, string d)
        {
            switch (p)
            {
                case ePatterns.Email:
                    try
                    {
                        System.Net.Mail.MailAddress m = new System.Net.Mail.MailAddress(d);
                        return true;
                    }
                    catch
                    {
                        return false;
                    }
                case ePatterns.Phone:
                    // Only Hungarian Phone numbers allowed here, if you want others, you need to change the pattern!
                    string PhoneNumberPatter = @"\+36-\d{2}-\d{3}-\d{4}";
                    Regex rxp = new Regex(PhoneNumberPatter, RegexOptions.Compiled | RegexOptions.IgnoreCase);
                    if (rxp.IsMatch(d)) return true;
                    break;
                case ePatterns.CreditCardNumber:
                    string CreditCardPatter = @"\d{8}-\d{8}-\d{8}";
                    Regex rxc = new Regex(CreditCardPatter, RegexOptions.Compiled | RegexOptions.IgnoreCase);
                    if (rxc.IsMatch(d)) return true;
                    break;
                case ePatterns.Date:
                    try
                    {
                        DateTime dateTime = Convert.ToDateTime(d);
                        if (dateTime.CompareTo(DateTime.Now)>=0)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    catch
                    {
                        return false;
                    }
            }
            return false;
        }
    }
}
