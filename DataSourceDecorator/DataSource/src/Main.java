public class Main {
    public static void main(String[] args) {
        DataSource ds =
                new CompressDecorator(
                    new EncryptionDecorator(
                        new StorageDataSource()
                )
        );
        ds.Write("DataSource with Decorators.");
        System.out.println("Rezultat: " + ds.Read());
    }
}
