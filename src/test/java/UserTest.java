

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.wallet.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserTest {
     double balance;

     double creditBalance;
     Map<String, String> transactions = new HashMap<>();

     Map<Date, String> toDoList = new HashMap<>();

    User user = new User("firstName", "lastName",  "phone",  "login",  "password",  0.00,  0.00, new ArrayList<>(), new Date());
    @Before
    public void setUp() {
                String transactionId = "5544433as";
                        transactions.put(transactionId, "message");
                        toDoList.put(new Date(11111), "message");
                    balance = 123.00;


    }
    @Test
    public void testDeposit() {
        double amount = 23.00;
        user.deposit(amount, new Date());
        Assert.assertEquals("Test balance process failed!", balance+amount, user.getBalance());
    }
    @Test
    public void testWithdraw() {
        double amount = 23.00;
        user.setBalance(0.00);
        user.withdraw(amount, new Date());
        Assert.assertEquals("Test balance process failed!", balance-amount,user.getBalance());
    }

    @Test
    public void testTakeCredit() {
        double credit = 2300.00;
        user.takeCredit(credit, new Date());
        Assert.assertEquals("Test balance process failed!", creditBalance + user.getCreditBalance());
    }
    @Test
    public void testPayCredit() {
        double amount = 23.00;
        user.payCredit(amount, new Date());
        Assert.assertEquals("Test balance process failed!", creditBalance - amount, user.getCreditBalance());
    }
}
