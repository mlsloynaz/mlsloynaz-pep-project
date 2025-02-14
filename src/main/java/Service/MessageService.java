package Service;
import java.util.*;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService(){
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    
    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message createMessage(Message message){
        String messageText = message.getMessage_text(); 
        int postedBy = message.getPosted_by(); // check if exist
     
        boolean userExist = this.accountDAO.hasAccountId(postedBy);
     

        if(!userExist || !this.isValidMessage(messageText)){
            return null;
        }

        return this.messageDAO.createMessage(message);
    }

    public Message updateMessage(int messageId, Message message){
        Message messageFromDB = this.messageDAO.getMessageById(messageId);
        if(messageFromDB == null ||  !this.isValidMessage(message.getMessage_text())){
            return null;
        }

        int rowsAffected = this.messageDAO.updateMessage(messageId, message);
        if(rowsAffected > 0 ){
            return this.messageDAO.getMessageById(messageId);
        }else{
            return null;
        }
       
    }

    public Message deleteMessage(int messageId){
        Message message = this.messageDAO.getMessageById(messageId);
        if(message!=null){
            boolean result = this.messageDAO.deleteMessage(messageId);
            if(result){
                return message;
            }
        }
        return null;
    }

    public List<Message> gelAllMessages(){

        return this.messageDAO.getAllMessages();

    }

    public Message getMessagesById(int messageId){

        return this.messageDAO.getMessageById(messageId);

    }

    public List<Message> getMessagesByUser(int userId){

        return this.messageDAO.getAllMessagesByUser(userId);

    }

    private boolean isValidMessage(String messageText){
        return  (messageText.length() > 0 && messageText.length() < 255);
    }
}
