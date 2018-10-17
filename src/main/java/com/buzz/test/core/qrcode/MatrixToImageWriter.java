package com.buzz.test.core.qrcode;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class MatrixToImageWriter {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = matrix.get(i, j) ? BLACK : WHITE;
                bufferedImage.setRGB(i, j, color);
            }
        }
        return bufferedImage;
    }

    public static void writeToFile(BitMatrix matrix, String fileType, File file, String logoUri) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        QRCodeFactory factory = new QRCodeFactory();
        image = factory.setMatrixLogo(image, logoUri);

        if (!ImageIO.write(image, fileType, file)) {
            System.err.println("fail to create QR code image!");
            throw new IOException(String.format("could not create QRCode image of fileType %s,%s", fileType, file));
        }
    }


    public static void writeToStream(BitMatrix matrix, String format, OutputStream outputStream, String logoUri) throws IOException {
        BufferedImage image = toBufferedImage(matrix);

        QRCodeFactory factory = new QRCodeFactory();
        image = factory.setMatrixLogo(image, logoUri);

        if (!ImageIO.write(image, format, outputStream)) {
            System.err.println("fail to create QR code image!");
            throw new IOException(String.format("could not create QRCode image of %s", format));
        }
    }
}
