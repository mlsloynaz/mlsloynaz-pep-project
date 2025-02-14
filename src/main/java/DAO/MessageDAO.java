package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
   
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> resultList = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet resulSet = preparedStatement.executeQuery();
           
            while(resulSet.next()){
                
                Message message = new Message(
                    resulSet.getInt("message_id"), 
                    resulSet.getInt("posted_by"), 
                    resulSet.getString("message_text"), 
                    resulSet.getLong("time_posted_epoch") );
                
                resultList.add(message);
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return resultList;
    }
 

    public List<Message> getAllMessagesByUser(int postedBy){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> resultList = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM message WHERE posted_by=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, postedBy);
            ResultSet resulSet = preparedStatement.executeQuery();
           
            while(resulSet.next()){
                
                Message message = new Message(
                    resulSet.getInt("message_id"), 
                    resulSet.getInt("posted_by"), 
                    resulSet.getString("message_text"), 
                    resulSet.getLong("time_posted_epoch") );
                
                resultList.add(message);
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return resultList;
    }


    public Message getMessageById(int messageId){
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, messageId);
            
            ResultSet resulSet = preparedStatement.executeQuery();
           
            while(resulSet.next()){
                
                Message message = new Message(
                    resulSet.getInt("message_id"), 
                    resulSet.getInt("posted_by"), 
                    resulSet.getString("message_text"), 
                    resulSet.getLong("time_posted_epoch") );
                
                return message;
                
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }


    public int updateMessage(int messageId, Message message){
        Connection connection = ConnectionUtil.getConnection();
        String messageText =  message.getMessage_text();
   
        try{
            String sql = "UPDATE message SET message_text = ? WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, messageText);
            preparedStatement.setInt(2, messageId);

            return  preparedStatement.executeUpdate();

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return 0;

    }


    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        int postedBy = message.getPosted_by();
        String messageText = message.getMessage_text();
        Long timePosted = message.getTime_posted_epoch();
       
        try{
            String sql = "INSERT INTO message (message_text, posted_by, time_posted_epoch) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, messageText);
            preparedStatement.setInt(2, postedBy);
            preparedStatement.setLong(3, timePosted);

            preparedStatement.executeUpdate();
           
            ResultSet resultKeys = preparedStatement.getGeneratedKeys();

            if(resultKeys.next()){
                int generatedMessageId = (int) resultKeys.getInt(1);

                return new Message(generatedMessageId, postedBy, messageText, timePosted ); 
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return null;
 
    }

    
    public boolean deleteMessage(int messageId){
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "DELETE FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, messageId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if(rowsAffected>0){
                return true;
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
