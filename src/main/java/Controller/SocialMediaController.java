package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    private AccountService accountService; 
    private MessageService messageService; 

     public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
     }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("register", this::registerAccount);
        app.post("login", this::loginAccount);
        app.post("messages", this::createMessage);
        app.patch("messages/{message_id}", this::updateMessage);
        app.delete("messages/{message_id}", this::deleteMessage);
        app.get("messages", this::getAllMessages);
        app.get("messages/{message_id}", this::getMessageById);
        app.get("accounts/{account_id}/messages", this::getUserMessages);
    

        return app;
    }

    private void registerAccount(Context ctx){ 
        try{
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account accountFromDB = this.accountService.registerAccount(account);
    
            if(accountFromDB != null){
                ctx.status(200); // better 201
                ctx.json(accountFromDB);
            }else{
                ctx.status(400);
            }
        }catch(JsonProcessingException ex){
            ctx.status(500);
        }
    }

    private void loginAccount(Context ctx){ 
        try{
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account accountFromDB = this.accountService.loginAccount(account);
    
            if(accountFromDB != null){
                ctx.status(200); // better 201
                ctx.json(accountFromDB);
            }else{
                ctx.status(401);
            }
        }catch(JsonProcessingException ex){
            ctx.status(500);
        }
    }

    private void createMessage(Context ctx){
        try{
            ObjectMapper om = new ObjectMapper();
            Message message = om.readValue(ctx.body(), Message.class);
            Message messageCreated = this.messageService.createMessage(message);
       
            if(messageCreated != null){
                ctx.status(200);
                ctx.json(messageCreated);
            }else {
                ctx.status(400); 
            }
        }catch(JsonProcessingException ex){
            ctx.status(500);
        }catch(Exception ex){
            ctx.status(400);
        }
    }

    private void updateMessage(Context ctx){
        try{
            ObjectMapper om = new ObjectMapper();
            Message message = om.readValue(ctx.body(), Message.class);
            int messageId =  Integer.parseInt(ctx.pathParam("message_id"));
            
            Message messageUpdated = this.messageService.updateMessage(messageId,message);
       
            if(messageUpdated != null){
                ctx.json(messageUpdated);
            }else {
                ctx.status(400); 
            }
        }catch(JsonProcessingException ex){
            ctx.status(500);
        }catch(Exception ex){
            ctx.status(400);
        }
    }

    private void deleteMessage(Context ctx){
        try{
            int messageId =  Integer.parseInt(ctx.pathParam("message_id"));
            
            Message messageDeleted = this.messageService.deleteMessage(messageId);
            
            ctx.status(200); 
            if(messageDeleted != null){
                ctx.json(messageDeleted);
            }
        }catch(Exception ex){
            ctx.status(400);
        }
    }

    private void getAllMessages(Context ctx){
        try{
            List<Message> result = this.messageService.gelAllMessages();
            ctx.json(result);

        }catch(Exception ex){
            ctx.status(400);
        }
    }

    private void getMessageById(Context ctx){
        try{
            int messageId = Integer.parseInt(ctx.pathParam("message_id"));
            Message result = this.messageService.getMessagesById(messageId);
            if( result!= null ){
                ctx.json(result);
            }else{
                ctx.result();
            }
       

        }catch(Exception ex){
            ctx.status(400);
        }
    }

    private void getUserMessages(Context ctx){
        try{
            int accopuntId = Integer.parseInt(ctx.pathParam("account_id"));
            List<Message> result = this.messageService.getMessagesByUser(accopuntId);
        
            ctx.json(result);

        }catch(Exception ex){
            ctx.status(400);
        }
    }
    
}