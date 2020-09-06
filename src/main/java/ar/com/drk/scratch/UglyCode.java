package ar.com.drk.scratch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UglyCode {

  private UserRepository userRepository = new UserRepository();
  private MailService mailService = new MailService();

  public User getNewUser(final String username, final String mailAddress, final Country country, final List<Group> groups) throws IOException {

    // user lookup
    User user = userRepository.findByMail(mailAddress);
    if (user == null) {
      user = new User(username, mailAddress, country);
      // Age restriction check
      final List<Group> restricted = getAllGroups().stream()
          .filter(group -> group.isCountryRestricted())
          .filter(group -> groups.contains(group))
          .collect(Collectors.toList());
      if (country != Country.US && !restricted.isEmpty()) {
        return null;
      }
      if (user.isShh()) {
        // Create user assets
        Path home = Paths.get("/home/" + username);
        Files.createDirectory(home);
      }
      final String message = "Welcome " + user.getName() + ", \n\nYour user has been created";
      mailService.send(user.getMailAddress(), message);
      user = userRepository.save(user);
    } else {
      final String message = "Hello " + user.getName() + ", \n\nWe've got a request to create a new user with your email address";
      mailService.send(user.getMailAddress(), message);
      user = null;
    }

    return user;
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
