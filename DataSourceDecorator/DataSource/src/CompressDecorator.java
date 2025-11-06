import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;

public class CompressDecorator extends BaseDataSourceDecorator {

    public CompressDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void Write(String data) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(output);
            gzip.write(data.getBytes());
            gzip.close();

            String rezultat = Base64.getEncoder().encodeToString(output.toByteArray());
            _wrappee.Write(rezultat);
        } catch (Exception e) {
            System.out.println("Eroare la compresie: " + e.getMessage());
        }
    }

    @Override
    public String Read() {
        try {
            String textCompresat = _wrappee.Read();
            if (textCompresat == null || textCompresat.isEmpty()) return "";

            byte[] bytesCompresati = Base64.getDecoder().decode(textCompresat);
            GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(bytesCompresati));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int c;
            while ((c = gzip.read()) != -1) {
                output.write(c);
            }
            gzip.close();

            return output.toString();
        } catch (Exception e) {
            System.out.println("Eroare la decompresie: " + e.getMessage());
            return "";
        }
    }
}
