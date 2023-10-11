package ru.wallet.model;

import ru.wallet.service.RegUser;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Класс пользователя с полями <b>username</b>  <b>password</b> <b>balance</b> <b>creditBalance</b>
 * @author Coolin Paul
 * @version 0.1
 * * <p>
 *  Класс с логикой работы кошелька с пользователем.
 */
public class User extends Person implements RegUser {
    private String login;

    private String password;

    private double balance;

    private double creditBalance;

    /** Поле таблицы со всеми транзакциями */
    private Map<String, String> transactions = new HashMap<>();

    /** Поле таблицы со всеми действиями пользователя */
    private Map<Date, String> toDoList = new HashMap<>();
    /**
     * Конструктор - создание нового пользователя
     * @see User#User()
     * @param firstName  - Фамилия
     * @param lastName - Имя
     * @param phone - Телефон
     * @param login - Логин
     * @param password - пароль
     * @param balance - баланс на счете
     * @param creditBalance - баланс кредита(долга)
     * @param transactions - транзакции
     */
    public User(String firstName, String lastName, String phone, String login, String password, double balance, double creditBalance, ArrayList<String> transactions, Date date) {
        super(firstName, lastName, phone);
        this.login = login;
        this.password = password;
        this.balance = balance;
        addTransaction(login +"@" + date +"//" + (transactions.size() + 1) + (1000 + (int) (Math.random() * 1000000))+ "Initial",String.format("Initial deposit - " + NumberFormat.getCurrencyInstance().format(balance) + " as on " + "%1$tD" + " at " + "%1$tT.", date));
    }
    public User(String firstName, String lastName, String phone, String login, String password, Date date) {
        super(firstName, lastName, phone);
        this.login = login;
        this.password = password;
    }

    public User() {}

    /**
     * Метод добавления транзакции в таблицу с транзакциями пользователя {@link User#transactions}
     */
    private void addTransaction(String transactionId, String message) {
        transactions.put(transactionId, message);
    }
    /**
     * Метод добавления транзакции в таблицу с транзакциями пользователя {@link User#transactions}
     * @return возвращает все транзакции пользователя
     */
    public ArrayList<String> getTransactions() {
        return new ArrayList<>(this.transactions.values());
    }

    /**
     * Метод добавления действия пользователя {@link User#toDoList}
     */
    public void addToDoList(Date date, String message) {
        toDoList.put(date, message);
    }

    /**
     * Метод добавления действия пользователя {@link User#toDoList}
     *  * @return возвращает все действия пользователя
     */
    public Map<Date, String> getToDoList() {
        return this.toDoList;
    }

    /**
     * Метод внесения средств на счет пользователя {@link User#deposit(double, Date)}
     *  @param amount - количество средств вносимых пользователем
     *  @param date - дата и время пополнения
     */
    @Override
    public void deposit(double amount, Date date) {
        String transactionId = login +"@" + date +"//" + (transactions.size() + 1) + (1000 + (int) (Math.random() * 1000000))+ "deposit";
        if(transactions.containsKey(transactionId)){
            addToDoList(new Date(), "Error Deposit");
            System.out.println("Transaction Id error");
            return;
        }
            balance += amount;
            addTransaction(transactionId, String.format(NumberFormat.getCurrencyInstance().format(amount) + " credited to your account. Balance - " + NumberFormat.getCurrencyInstance().format(balance) + " as on " + "%1$tD" + " at " + "%1$tT.", date));
            addToDoList(new Date(), "Deposit");
        }

    /**
     * Метод снятия средств со счета пользователя {@link User#withdraw(double, Date)}
     *  @param amount - количество средств снимаемых пользователем
     *  @param date - дата и время снятия
     */
    public void withdraw(double amount, Date date) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            addToDoList(new Date(), "Error Withdraw");
            return;
        }

        String transactionId = login +"@" + date +"//" + (transactions.size() + 1) + (1000 + (int) (Math.random() * 1000000))+ "withdraw";
        if(transactions.containsKey(transactionId)){
            addToDoList(new Date(), "Error Transaction Id Withdraw");
            System.out.println("Transaction Id error");
            return;
        }
        balance -= amount;
        addTransaction(transactionId,String.format(NumberFormat.getCurrencyInstance()
                .format(amount) + " debited from your account. Balance - " + NumberFormat.getCurrencyInstance().format(balance) + " as on " + "%1$tD" + " at " + "%1$tT.", date));
            addToDoList(new Date(), "Withdraw");
    }
    /**
     * Метод взятия кредита  пользователя {@link User#takeCredit(double, Date)}
     *  @param credit - объем кредитного тела взятым пользователем
     *  @param date - дата и время взятия кредита
     */
    @Override
    public void takeCredit(double credit, Date date) {
        if  (creditBalance > 0) {
            System.out.println("Pay off your owе.");
            addToDoList(new Date(), "Error Take credit");
            return;
        }

        String transactionId = login +"@" + date +"//" + (transactions.size() + 1) + (1000 + (int) (Math.random() * 1000000))+ "credit";
        if(transactions.containsKey(transactionId)){
            System.out.println("Transaction Id error");
            addToDoList(new Date(), "Error Transaction Id Take credit");
            return;
        }
        creditBalance += credit;
        addTransaction(transactionId,String.format(NumberFormat.getCurrencyInstance()
                .format(creditBalance) + " credit from your account. Credit - " + NumberFormat.getCurrencyInstance().format(creditBalance) + " as on " + "%1$tD" + " at " + "%1$tT.", date));
        addToDoList(new Date(), "Take credit");
    }
    /**
     * Метод пополнения  кредитного тела  пользователя {@link User#payCredit(double, Date)}
     *  @param amount - объем погашения кредитного тела
     *  @param date - дата и время погашения кредита
     */
    @Override
    public void payCredit(double amount, Date date) {

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            addToDoList(new Date(), "Error Pay credit");
            return;
        }


        String transactionId = login +"@" + date +"//" + (transactions.size() + 1) + (1000 + (int) (Math.random() * 1000000))+ "credit";
        if(transactions.containsKey(transactionId)){
            System.out.println("Transaction Id error");
            addToDoList(new Date(), "Error Transaction Id Pay credit");
            return;
        } else if(creditBalance < amount) {
            creditBalance -= creditBalance;
            balance -= creditBalance;
        } else if(creditBalance >= amount){
            creditBalance -= amount;
            balance -= amount;
        }
            addTransaction(transactionId,String.format(NumberFormat.getCurrencyInstance()
                .format(creditBalance) + " pay credit from your account. Balance - " + NumberFormat.getCurrencyInstance().format(balance) + ". Credit balance - " + NumberFormat.getCurrencyInstance().format(creditBalance) + " as on " + "%1$tD" + " at " + "%1$tT.", date));
            addToDoList(new Date(), "Pay credit");
    }



    @Override
    public String toString() {
        return "Customer{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Double.compare(user.getBalance(), getBalance()) != 0) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        return getTransactions() != null ? getTransactions().equals(user.getTransactions()) : user.getTransactions() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        return result;
    }
        public String getLogin() {
            return login;
        }

        public void setLogin(String username) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

    public double getCreditBalance() {
        return balance;
    }

    public void setCreditBalance(double balance) {
        this.balance = balance;
    }

}
