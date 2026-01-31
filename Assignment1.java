
abstract class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Housing expenditure " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Spended money  -" + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public abstract String getAccountDetails();
}


class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double interestRate) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }

    @Override
    public String getAccountDetails() {
        return "Savings Account " + getAccountNumber() +
               ", Balance: Rs " + balance +
            //    ", Interset: Rs"+ (interest) +
               ", Rate: " + (interestRate * 100) + "%";
    }
}


class CheckingAccount extends BankAccount {

    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolderName, double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= -overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " (Overdraft allowed)");
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }

    @Override
    public String getAccountDetails() {
        return "Checking Account #" + getAccountNumber() +
               ", Balance: Rs " + balance +
               ", Limit: Rs " + overdraftLimit;
               
    }
}

public class BankManager{
    public static void main(String[] args){
        BankAccount  savings = new SavingsAccount("SBI202","Abhijeet Kumar",1000.05);
        BankAccount checking  = new CheckingAccount("SBI101","Mannu",2002.95);
        savings.deposit(12000);
        checking.deposit(1000);
        System.out.println(savings.getAccountDetails());
        System.out.println(checking.getAccountDetails());
        System.out.println();
        checking.withdraw(11999);
        savings.withdraw(11999);

        System.out.println(savings.getAccountDetails());
        System.out.println(checking.getAccountDetails());
    }
}
