import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionDecorator extends BaseDataSourceDecorator {

    private static final String CHEIE = "1234567890ABCDEF";

    public EncryptionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void Write(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(CHEIE.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] criptat = cipher.doFinal(data.getBytes());

            String rezultat = Base64.getEncoder().encodeToString(criptat);
            _wrappee.Write(rezultat);
        } catch (Exception e) {
            System.out.println("Eroare la criptare: " + e.getMessage());
        }
    }

    @Override
    public String Read() {
        try {
            String textCriptat = _wrappee.Read();
            if (textCriptat == null || textCriptat.isEmpty()) return "";

            SecretKeySpec key = new SecretKeySpec(CHEIE.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodat = Base64.getDecoder().decode(textCriptat);
            byte[] decriptat = cipher.doFinal(decodat);

            return new String(decriptat);
        } catch (Exception e) {
            System.out.println("Eroare la decriptare: " + e.getMessage());
            return "";
        }
    }
}
