package com.buzz.test.core.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeFactory {

    public BufferedImage setMatrixLogo(BufferedImage matrixImage, String logoUri) throws IOException {

        Graphics2D g2 = matrixImage.createGraphics();

        int matrixWidth = matrixImage.getWidth();
        int matrixHeight = matrixImage.getHeight();

        BufferedImage logo = ImageIO.read(new File(logoUri));

        g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeight / 5 * 2, matrixWidth / 5, matrixHeight / 5, null);

        //draw stroke
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);

        //draw RoundRectangle
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeight / 5 * 2, matrixWidth / 5, matrixHeight / 5, 20, 20);
        g2.setColor(Color.WHITE);

        g2.draw(round);

        // set logo
        BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);

        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeight / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeight / 5 - 4, 20, 20);
        g2.setColor(new Color(128, 128, 128));
        g2.draw(round2);

        g2.dispose();
        matrixImage.flush();

        return matrixImage;
    }

    public void createQRCode(String content, String fileType, String outFileUri, String logoUri, int... size) throws WriterException, IOException {
        int width = 430;
        int height = 430;

        if (size.length == 2) {
            width = size[0];
            height = size[1];
        } else if (size.length == 1) {
            width = height = size[0];
        }

        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        File outputFile = new File(outFileUri);
        MatrixToImageWriter.writeToFile(bitMatrix, fileType, outputFile, logoUri);
    }
}
