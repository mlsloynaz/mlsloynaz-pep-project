package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO; 

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account){
   
        boolean isAlreadyRegistered = this.accountDAO.getAccountByUserName(account.getUsername());

        if(!this.isAccountValid(account) || isAlreadyRegistered){
            return null;
        }

        return this.accountDAO.registerAccount(account);
    }

    public Account loginAccount(Account account){
        if(!this.isAccountValid(account)){
            return null;
        }

        return this.accountDAO.loginAccount(account);
    }

    private boolean isAccountValid(Account account){
        String userName = account.getUsername();
        String password = account.getPassword();

        return (userName.length() > 0 && password.length() >= 4); 
    }
}
