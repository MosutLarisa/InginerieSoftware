public class Main {
    public static void main(String[] args){
        Server server = new Server();
        User ionescu = new User(1, "Ionescu", server);
        User andrei = new User(2, "Andrei", server);
        User alin = new User(3, "Alin", server);

        server.RegisterUser(ionescu);
        server.RegisterUser(andrei);
        server.RegisterUser(alin);

        ionescu.SendMessage(3,"Hello Alin!");
        server.Unregister(alin);
        ionescu.SendMessage(3,"Another message for Alin.");
        ionescu.SendMessage(2,"Hello Andrei!");

        server.RegisterUser(alin);
        alin.setIsBusy(true);
        andrei.SendMessage(3, "Message from Andrei");
        alin.setIsBusy(false); // devine disponibil și primește mesajul
    }
}
