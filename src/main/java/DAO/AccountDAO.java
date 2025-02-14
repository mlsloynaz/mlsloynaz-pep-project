package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {
    
    public Account registerAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        String username = account.getUsername();
        String password = account.getPassword();

        try{
            String sql = "INSERT INTO account (username, password) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();
           
            ResultSet resultKeys = preparedStatement.getGeneratedKeys();

            if(resultKeys.next()){
                int generatedAccountId = (int) resultKeys.getInt(1);
                return new Account(generatedAccountId, username, password); 
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public Account loginAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        String username = account.getUsername();
        String password = account.getPassword();

        try{
            String sql = "SELECT * FROM account WHERE username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resulSet = preparedStatement.executeQuery();
           
            if(resulSet.next()){
                int accountId = resulSet.getInt(1);
                return new Account(accountId, username, password); 
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean getAccountByUserName(String username){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM account WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, username);

            ResultSet resulSet = preparedStatement.executeQuery();
           
            if(resulSet.next()){
                return true; 
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean hasAccountId(int accountId){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM account WHERE account_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, accountId);

            ResultSet resulSet = preparedStatement.executeQuery();
           
            if(resulSet.next()){
                return true; 
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
