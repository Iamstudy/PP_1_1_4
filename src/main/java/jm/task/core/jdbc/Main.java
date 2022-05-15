package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Василий", "Васин", (byte) 27);
        userService.saveUser("Алексей", "Лёшин", (byte) 41);
        userService.saveUser("Владимир", "Вовин", (byte) 36);
        userService.saveUser("Михаил", "Мишин", (byte) 61);

        for(User users : userService.getAllUsers()) {
            System.out.println(users.toString());
        }
         userService.cleanUsersTable();
         userService.dropUsersTable();

    }
}
