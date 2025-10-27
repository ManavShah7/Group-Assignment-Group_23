package Business.UserAccounts;

import java.util.ArrayList;
import Business.Profiles.Profile;

/**
 * Manages all system user accounts and authentication.
 * @author Manav
 */
public class UserAccountDirectory {

    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public UserAccount newUserAccount(Profile p, String un, String pw, Role role) {
        UserAccount ua = new UserAccount(p, un, pw, role);
        userAccountList.add(ua);
        return ua;
    }

    public UserAccount authenticateUser(String un, String pw) {
        for (UserAccount ua : userAccountList) {
            if (ua.isValidUser(un, pw)) {
                return ua;
            }
        }
        return null;
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }
}
