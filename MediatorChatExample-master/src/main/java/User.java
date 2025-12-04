public class User implements IUser {
    private int id;
    private String name;
    private IServer server;
    private boolean isBusy;

    public User(int id, String name, IServer server)
    {
        this.id = id;
        this.name = name;
        this.server = server;
    }

    @Override
    public void SendMessage(int userId, String message)
    {
        server.SendMessage(id, userId, message);
    }

    @Override
    public void ReceiveMessage(String userName, String message){
        System.out.println(name + " received message from " + userName + ": " + message);
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public void setIsBusy(boolean isBusy) {
        boolean wasBusy = this.isBusy;
        this.isBusy = isBusy;

        if(wasBusy && !isBusy){
            System.out.println(name + " is now available and will receive pending messages if any.");
            server.SendMessage(-1, id, ""); //  verif mesajelor
        }
    }

    @Override
    public boolean getIsBusy() { return isBusy; }
}
