package Business.UserAccounts;

import Business.Profiles.Profile;

/**
 * Represents login credentials and associated user profile.
 * Updated and cleaned for Access Control Part 1.
 * @author Manav
 */
public class UserAccount {

    private Profile profile;
    private String username;
    private String password;
    private Role role;

    public UserAccount(Profile profile, String username, String password, Role role) {
        this.profile = profile;
        this.username = username != null ? username.trim() : "";
        this.password = password != null ? password.trim() : "";
        this.role = role;
    }

    // --- Getters ---
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public Role getRole() {
        return role;
    }

    // --- Setters ---
    public void setUsername(String username) {
        this.username = username != null ? username.trim() : "";
    }

    public void setPassword(String password) {
        this.password = password != null ? password.trim() : "";
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // --- Utility methods ---
    /** Returns associated person’s ID if available */
    public String getPersonId() {
        return (profile != null && profile.getPerson() != null)
                ? profile.getPerson().getPersonId()
                : "";
    }

    /** Validates username-password match */
    public boolean isValidUser(String un, String pw) {
        return username.equalsIgnoreCase(un) && password.equals(pw);
    }

    /** Checks if this user matches a given person ID */
    public boolean isMatch(String id) {
        return getPersonId().equals(id);
    }

    /** Returns the displayable role name (fallback: UNASSIGNED) */
    public String getDisplayRole() {
        return (role != null) ? role.name() : "UNASSIGNED";
    }

    /** Returns the associated person profile */
    public Profile getAssociatedPersonProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return username + " (" + getDisplayRole() + ")";
    }
}
