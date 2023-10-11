package ru.wallet;

import ru.wallet.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Консольное приложение электронный кошелек.
 * @author Coolin Paul
 * @version 0.1
 * * <p>
 *  * Основной класс для запуска программы.
 */
public class Wallet {
    /** Поле инициации счета(по умолчанию счет пустой) */
    private static double amount = 0;

    /** Поле таблицы со всеми пользователями */
    Map<String, User> userMap;

    /** Конструктор - создание нового кошелька и списка пользователя*/
    Wallet() {
        userMap = new HashMap<String, User>();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        User user;
        Wallet wallet = new Wallet();
        int choice;
        outer:
        while (true) {
            System.out.println("\n-------------------");
            System.out.println("E-Wallet");
            System.out.println("-------------------\n");
            System.out.println("1. Register user.");
            System.out.println("2. Login.");
            System.out.println("3. Exit.");
            System.out.print("\nEnter your choice : ");
            choice = sc.nextInt();
            sc.nextLine();
            /** Отрисовка основного дисплея при помощи switch*/
            switch (choice) {
                case 1:
                    System.out.print("Enter First Name : ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Last Name : ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter contact number : ");
                    String phone = sc.nextLine();
                    System.out.println("Set Username : ");
                    String login = sc.next();
                    while (wallet.userMap.containsKey(login)) {
                        System.out.println("Username already exists. Set again : ");
                        login = sc.next();
                    }
                    System.out.println("Set a password:");
                    String password = sc.next();
                    sc.nextLine();
                    /** Создание нового пользователя и занесение его в общую таблицу*/
                    user = new User(firstName, lastName, phone, login, password, new Date());
                    wallet.userMap.put(login, user);
                    user.addToDoList(new Date(), "Register user");
                    break;

                case 2:
                    System.out.println("Enter username : ");
                    login = sc.next();
                    sc.nextLine();
                    System.out.println("Enter password : ");
                    password = sc.next();
                    sc.nextLine();
                    if (wallet.userMap.containsKey(login)) {
                        user = wallet.userMap.get(login);
                        if (user.getPassword().equals(password)) {
                            while (true) {
                                /** Отрисовка  дисплея пользователя при помощи switch*/
                                user.addToDoList(new Date(), "Login");
                                System.out.println("\n-------------------");
                                System.out.println("W  E  L  C  O  M  E");
                                System.out.println("-------------------\n");
                                System.out.println("1. Deposit.");
                                System.out.println("2. Withdraw.");
                                System.out.println("3. Take credit.");
                                System.out.println("4. Pay of credit.");
                                System.out.println("5. Last transactions.");
                                System.out.println("6. User information.");
                                System.out.println("7. Log out.");
                                System.out.print("\nEnter your choice : ");
                                choice = sc.nextInt();
                                sc.nextLine();
                                switch (choice) {
                                    /** использование функции Deposit*/
                                    case 1:
                                        System.out.print("Enter amount : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        user.deposit(amount, new Date());
                                        break;
                                    /** использование функции Withdraw*/
                                    case 2:
                                        System.out.print("Enter withdraw : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        user.withdraw(amount, new Date());
                                        break;
                                    /** использование функции Take Сredit*/
                                    case 3:
                                        System.out.print("Enter credit body : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        user.takeCredit(amount, new Date());
                                        break;
                                    case 4:
                                        System.out.print("Enter pay credit : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        user.payCredit(amount, new Date());
                                        break;
                                    /** использование функции Pay Сredit*/
                                    case 5:
                                        for (String transactions : user.getTransactions()) {
                                            System.out.println(transactions);
                                        }
                                        user.addToDoList(new Date(), "Show all transaction");
                                        break;
                                    /** использование функции User information*/
                                    case 6:
                                        System.out.println("User First name: " + user.getFirstName());
                                        System.out.println("User Last name : " + user.getLastName());
                                        System.out.println("User Login : " + user.getLogin());
                                        System.out.println("User phone number : " + user.getPhone());
                                        System.out.println("User deposit : " + user.getBalance());
                                        System.out.println("User credit : " + user.getCreditBalance());
                                        break;
                                    case 7:
                                        /** использование функции User Log out*/
                                        user.addToDoList(new Date(), "Logout");
                                        continue outer;
                                    default:
                                        System.out.println("Wrong choice !");
                                }
                            }
                        } else {
                            System.out.println("Wrong login/password.");
                        }
                    } else {
                        System.out.println("Wrong login/password.");
                    }
                    break;

                case 3:
                    System.out.println("\nTake care!. Hakuna Matata!.");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Wrong choice !");
            }}}}
