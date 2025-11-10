import java.util.*;
import javafx.scene.control.*;
import javafx.collections.*;

public class Bank extends TreeMap<Integer, BankAccount>
{
    public BankAccount addAccount(String name)
    {
        BankAccount acc = new BankAccount(name);
        put(acc.getId(),acc);
        return acc;
    }
    
    public BankAccount removeAccount(int id)
    {
        BankAccount removed = null;
        if (containsKey(id))
        {
            removed = remove(id);
        }
        return removed;
    }
    
    public BankAccount deposit(int id)
    {
        BankAccount deposited = null;
        if (containsKey(id))
        {
            double currAmount = get(id).getBalance();
            double toDeposit = enterAmount();
            deposited = get(id);
            double newBalance = currAmount+toDeposit;
            get(id).setBalance(newBalance);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deposit Message");
            alert.setHeaderText("Deposit Message");
            alert.setContentText("Account with ID " + id 
            + " and name " + deposited.getName() + " has been deposited with " 
            + toDeposit + ". New balance is " + newBalance);
            alert.showAndWait();
        }
        return deposited;
    }
    
    public BankAccount withdraw(int id)
    {
        BankAccount withdrawn = null;
        if (containsKey(id))
        {
            double currAmount = get(id).getBalance();
            double toWithdraw = enterAmount();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Withdrawal Message");
            alert.setHeaderText("Withdrawal Message");
            if (toWithdraw > currAmount)
            {
                alert.setContentText("Insufficient funds in account with ID " 
                + id + ". Current balance is " + currAmount + ".");
            }
            else {
                withdrawn = get(id);
                double newBalance = currAmount-toWithdraw;
                get(id).setBalance(newBalance);
                alert.setContentText("Account with ID " + id + 
                " and name " + withdrawn.getName() + 
                " has been withdrawn with " + toWithdraw + 
                ". New balance is " + newBalance + ".");
            }
            alert.showAndWait();
        }
        return withdrawn;
    }
    
    public int enterAccount()
    {
        int id;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Account ID");
        dialog.setHeaderText("Enter an ID associated with an account in the bank: ");
        dialog.setContentText("Account ID: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent())
        {
            try
            {
                id = Integer.parseInt(optional.get());
            }
            catch (NumberFormatException err)
            {
                return enterAccount();
            }
        }
        else
        {
            return enterAccount();
        }
        return id;
    }
    
    private double enterAmount()
    {
        double amount;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Cash Amount");
        dialog.setHeaderText("Enter a valid cash amount: ");
        dialog.setContentText("Cash: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent())
        {
            try
            {
                amount = Double.parseDouble(optional.get());
            }
            catch (NumberFormatException err)
            {
                return enterAccount();
            }
        }
        else
        {
            return enterAccount();
        }
        return amount;
    }
    
    public ObservableList<BankAccount> toObservableList()
    {
        Collection<BankAccount> values = values();
        Iterator<BankAccount> iter = values.iterator();
        ObservableList<BankAccount> list = FXCollections.observableArrayList();
        if (values.size() != 0) {
            while (iter.hasNext())
            {
                list.add(iter.next());
            }
        }
        return list;
    }
    
    public BankAccount createAccount()
    {
        BankAccount newAccount;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Account Name");
        dialog.setHeaderText("Enter a name for the new account: ");
        dialog.setContentText("Name: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent())
        {
            newAccount = new BankAccount(optional.get());
            put(newAccount.getId(), newAccount);
        }
        else
        {
            return createAccount();
        }
        return newAccount;
    }
    
}
