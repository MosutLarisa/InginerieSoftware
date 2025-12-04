import java.util.*;

public class Server implements IServer{
    private Map<Integer, IUser> users = new HashMap<>();
    private Map<Integer, List<String>> pendingMessages = new HashMap<>();

    public Server() {}

    @Override
    public void RegisterUser(IUser user)
    {
        if(users.containsKey(user.getId())) return;

        users.put(user.getId(), user);
        System.out.println(user.getName() + " has connected.");

        // mesaje salvate dacă sunt
        if(pendingMessages.containsKey(user.getId())){
            System.out.println("Delivering stored messages to " + user.getName());
            for(String msg : pendingMessages.get(user.getId())){
                user.ReceiveMessage("Stored Message", msg);
            }
            pendingMessages.remove(user.getId());
        }
    }

    @Override
    public void Unregister(IUser user)
    {
        if(!users.containsKey(user.getId())) return;
        System.out.println(user.getName() + " has disconnected.");
        users.remove(user.getId());
    }

    @Override
    public void SendMessage(int userIdSender, int userIdReceiver, String message){
        IUser receiver = users.get(userIdReceiver);
        IUser sender = users.get(userIdSender);

        if(sender == null) return;

        if(receiver == null || receiver.getIsBusy()) {
            pendingMessages.putIfAbsent(userIdReceiver, new ArrayList<>());
            pendingMessages.get(userIdReceiver)
                    .add("From " + sender.getName() + ": " + message);

            System.out.println("Message stored because receiver is offline or busy.");
            return;
        }

        receiver.ReceiveMessage(sender.getName(), message);
    }
}
