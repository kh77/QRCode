import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class QrCodeExample {
    static String directoryName = "C:\\QRCode";
    static String path = directoryName + "\\example.jpg";


    public static void main(String[] args) throws IOException, WriterException, NotFoundException {
        generateQrCodeIntoJpg();
        readQrCodeImageText();
    }

    public static void generateQrCodeIntoJpg() throws WriterException, IOException {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String data = "Hello, I am java developer";

        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, 400, 400);

        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
    }

    public static void readQrCodeImageText() throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(path));

        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(bufferedImage)));

        Result result = new MultiFormatReader().decode(bitmap);
        System.out.println(result.getText());
    }
}
