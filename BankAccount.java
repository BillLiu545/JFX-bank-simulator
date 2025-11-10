import java.util.*;
import javafx.scene.control.*;
public class BankAccount
{
    private String name;
    private int id;
    private double balance;
    public BankAccount(String name)
    {
        Random rand = new Random();
        this.id = rand.nextInt(10000);
        this.name = name;
        this.balance = 0;
    }
    
    public double getBalance()
    {
        return this.balance;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setBalance(double newBalance)
    {
         this.balance = newBalance;
    }
    
    public String toString()
    {
        return this.name + " - ID: " + this.id;
    }
    
}