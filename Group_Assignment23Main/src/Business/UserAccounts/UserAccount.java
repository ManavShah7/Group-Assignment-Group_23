package Business.UserAccounts;

import Business.Profiles.Profile;

/**
 * Represents login credentials and associated user profile.
 * Updated for Part 1 Access-Control compliance.
 * @author Jaya
 */
public class UserAccount {

    private Profile profile;
    private String username;
    private String password;
    private Role role;

    public UserAccount(Profile profile, String username, String password, Role role) {
        this.profile = profile;
        this.username = username.trim();
        this.password = password.trim();
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Profile getProfile() { return profile; }
    public Role getRole() { return role; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }

    public String getPersonId() {
        return (profile != null && profile.getPerson() != null)
                ? profile.getPerson().getPersonId()
                : "";
    }

    public boolean isValidUser(String un, String pw) {
        return username.equalsIgnoreCase(un) && password.equals(pw);
    }

    public boolean isMatch(String id) {
        return getPersonId().equals(id);
    }

    public String getDisplayRole() {
        return role != null ? role.name() : "UNASSIGNED";
    }

    public Profile getAssociatedPersonProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return username + " (" + getDisplayRole() + ")";
    }
}
