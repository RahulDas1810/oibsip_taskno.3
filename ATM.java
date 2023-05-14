import java.util.*;
class ATM{
    public static void main(String[] args){
        
        double accountBalance = 28000.50;
        String history="";
        Scanner in=new Scanner(System.in);

        Account ac=new Account();
        Deposit dep=new Deposit();
        Withdraw w=new Withdraw();
        Transfer t=new Transfer();

        System.out.println("\n********************Welcome********************\n");
        System.out.print("Enter your user id: ");
        String u_id=in.next(); 
        System.out.print("Enter your pin: ");
        int p=in.nextInt();
        int check=ac.login(u_id,p);
        if(check==0){
            System.out.println("!!Error!!");
            System.out.println("Invalid User id and Pin\n");
        }
        else{
            System.out.println("\n**Login succesful**");
            int ch=0;
            while(ch!=6){
                System.out.println("\nPress 1 to Deposit          Press 2 to WithDraw");
                System.out.println("Press 3 for Transfer        Press 4 for Transaction History");
                System.out.println("Press 5 for View Balance    Press 6 to Exit");
                System.out.print("Please enter your choice: ");
                ch=in.nextInt(); 
                int flag;
                switch(ch){
                    case 1:  
                            System.out.print("\nEnter amount to be deposited: ");
                            double dep_amt= in.nextDouble();
                            flag=dep.deposit(dep_amt);
                            if(flag==1){
                                accountBalance+=dep_amt;
                                System.out.println("Total amount in your account is Rs."+accountBalance);
                                history = history.concat("\nDeposit of Rs. " + dep_amt);
                            }
                            System.out.println("-------------------------------------------------");
                            break;

                    case 2: 
                            System.out.print("\nEnter amount to be withdrawn: ");
                            double w_amt= in.nextDouble();
                            flag=w.withdraw(w_amt,accountBalance);
                            if(flag==1){
                                accountBalance-=w_amt;
                                System.out.println("Total amount in your account is Rs."+accountBalance);
                                history = history.concat("\nWithdrawal of Rs. " + w_amt);
                            }
                            System.out.println("-------------------------------------------------");
                            break;
                    
                    case 3: 
                            System.out.print("\nPlease enter the recipient's user Id: ");
                            String recipient_uid=in.next();
                            System.out.print("Enter the amount to be transferred: ");
                            double transfer_amt = in.nextDouble();
                            flag=t.transfer(recipient_uid,transfer_amt,accountBalance);
                            if(flag==1){
                                accountBalance-=transfer_amt;
                                System.out.println("Total amount in you account is Rs." +accountBalance);
                                history = history.concat("\nTransferred Rs. "+ transfer_amt+" to "+recipient_uid);
                            }
                            System.out.println("-------------------------------------------------");
                            break;

                    case 4:
                            System.out.println("\nTransaction history of user Id " + ac.user_id);
                            System.out.println(history);
                            System.out.println("-------------------------------------------------");
                            break;

                    case 5:
                            System.out.println("\nTotal balance in user Id "+ac.user_id);
                            System.out.println("Rs. "+accountBalance);
                            System.out.println("-------------------------------------------------");
                            break;
                    
                    case 6:
                            break;
                            
                    default:
                            System.out.println("!!Error!!\nEnter a valid choice......");
                }
            }
            System.out.println("\n****Thankyou for visiting****\n");
        } 
    }
}
class Account{
    String user_id= "ABCD1234";
	int pin = 1673;
    public int login(String u_id,int p){
        if(u_id.equals(user_id)==true && p==pin)
            return 1;
        else
            return 0; 
    }
} 
 
class Deposit{
    public int deposit(double amt){
        if(amt<0.00){
            System.out.println("\n!!Please enter correct amount!!");
            return 0; 
        }
        else if(amt>10000){
            System.out.println("\n!!Limit exceded!!.\nLimit is Rs.10000");
            return 0;
        }
        else{
            System.out.println("\n**Deposit Successfull**\n");
            return 1;
        }
    }
}
class Withdraw{
    public int withdraw(double amt,double ac_bal){
        if(amt>10000){
            System.out.println("\n!!Limit exceded!!.\nLimit is Rs.10000");
            return 0;
        }
        else if(amt>ac_bal) {
            System.out.println("\n!!Error!!\tInsufficient Balance.");
            return 0;
        }
        else {
            System.out.println("\n**Withdrawal succesful**\n");
            return 1;
        }
    }
}
class Transfer{
    public int transfer(String id,double amt,double ac_bal){
        if(amt<=ac_bal){
            if(amt<=10000){
                System.out.println("\nSuccesfully transferred Rs. "+amt+" to User Id "+id);
                return 1;
            }
            else{
                System.out.println("\nLimit is 10000.00.\n");
                return 0;
            }
        }
        else{
            System.out.println("\n!!Error!!\tInsufficient Balance.");
            return 0;
        }
    }
}
