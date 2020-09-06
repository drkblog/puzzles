package ar.com.drk.scratch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UglyCodeFixed {

  private UserRepository userRepository = new UserRepository();
  private MailService mailService = new MailService();

  public User createNewUserAndNotity(final String username, final String mailAddress, final Country country, final List<Group> groups) throws IOException {

    assertMailNotTakenAndNotify(mailAddress);
    assertCountryNotRestrictedForGroups(country, groups);

    final User user = new User(username, mailAddress, country);

    createUserAssetsIfNeeded(user);
    notifyUserCreated(user);

    return userRepository.save(user);
  }

  private void assertMailNotTakenAndNotify(final String mailAddress) {
    final User existingUser = userRepository.findByMail(mailAddress);
    if (existingUser != null) {
      final String message = "Hello " + existingUser.getName() + ", \n\nWe've got a request to create a new user with your email address";
      mailService.send(existingUser.getMailAddress(), message);
      throw new RuntimeException("Mail already taken");
    }
  }

  private void assertCountryNotRestrictedForGroups(final Country country, final List<Group> groups) {
    final List<Group> restricted = getAllGroups().stream()
        .filter(group -> group.isCountryRestricted())
        .filter(group -> groups.contains(group))
        .collect(Collectors.toList());
    if (country != Country.US && !restricted.isEmpty()) {
      throw new RuntimeException("Country is restricted for groups");
    }
  }

  private void createUserAssetsIfNeeded(final User user) throws IOException {
    if (user.isShh()) {
      Path home = Paths.get("/home/" + user.getUsername());
      Files.createDirectory(home);
    }
  }

  private void notifyUserCreated(final User user) {
    final String message = "Welcome " + user.getName() + ", \n\nYour user has been created";
    mailService.send(user.getMailAddress(), message);
  }

  private List<Group> getAllGroups() {
    return null;
  }

  private static class User {
    public User(final String username, final String mail, final Country country) {
    }

    public String getMailAddress() {
      return "";
    }

    public String getName() {
      return "";
    }

    public boolean isShh() {
      return false;
    }

    public String getUsername() {
      return "";
    }
  }

  private static class Group {
    public boolean isCountryRestricted() {
      return true;
    }
  }

  private static class UserRepository {
    public User findByMail(final String mail) {
      return null;
    }

    public User save(final User user) {
      return null;
    }
  }

  private static class MailService {
    public void send(final String mailAddress, final String s) {
    }
  }

  private static enum Country {
    US,
    ARGENTINA;
  }
}
