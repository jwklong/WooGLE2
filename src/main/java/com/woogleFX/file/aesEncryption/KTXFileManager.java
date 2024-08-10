package com.woogleFX.file.aesEncryption;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;

public class KTXFileManager {

    public static BufferedImage readKTXImage(Path ktxImagePath) {

        // TODO: figure this out

        try {

            ByteArrayInputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(ktxImagePath));

            inputStream.skipNBytes(50);

            String header = new String(inputStream.readNBytes(12));
            System.out.println("Header: " + header);

            int endianness = ByteBuffer.wrap(inputStream.readNBytes(4)).getInt();
            System.out.println("Endianness: " + endianness);

            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;

            long glType = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (glType < 0) {
                glType += (1L << 32);
                // glType ^= (0xFFFFFFFFL);
            }
            System.out.println("glType: " + glType);

            long glTypeSize = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (glTypeSize < 0) {
                glTypeSize += (1L << 31);
                //glTypeSize ^= (0xFFFFL);
            }
            System.out.println("glTypeSize: " + glTypeSize);

            long glFormat = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (glFormat < 0) {
                glFormat += (1L << 32);
                // glFormat ^= (0xFFFFFFFFL);
            }
            System.out.println("glFormat: " + glFormat);

            long glInternalFormat = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (glInternalFormat < 0) {
                glInternalFormat += (1L << 32);
                // glInternalFormat ^= (0xFFFFFFFFL);
            }
            System.out.println("glInternalFormat: " + glInternalFormat);

            long glBaseInternalFormat = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (glBaseInternalFormat < 0) {
                glBaseInternalFormat += (1L << 32);
                // glBaseInternalFormat ^= (0xFFFFFFFFL);
            }
            System.out.println("glBaseInternalFormat: " + glBaseInternalFormat);

            long pixelWidth = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (pixelWidth < 0) {
                pixelWidth += (1L << 32);
                // pixelWidth ^= (0xFFFFFFFFL);
            }
            System.out.println("pixelWidth: " + pixelWidth);

            long pixelHeight = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (pixelHeight < 0) {
                pixelHeight += (1L << 32);
                // pixelHeight ^= (0xFFFFFFFFL);
            }
            System.out.println("pixelHeight: " + pixelHeight);

            long pixelDepth = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (pixelDepth < 0) {
                pixelDepth += (1L << 32);
                // pixelDepth ^= (0xFFFFFFFFL);
            }
            System.out.println("pixelDepth: " + pixelDepth);

            long numberOfArrayElements = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (numberOfArrayElements < 0) {
                numberOfArrayElements += (1L << 32);
                // numberOfArrayElements ^= (0xFFFFFFFFL);
            }
            System.out.println("numberOfArrayElements: " + numberOfArrayElements);

            long numberOfFaces = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (numberOfFaces < 0) {
                numberOfFaces += (1L << 32);
                // numberOfFaces ^= (0xFFFFFFFFL);
            }
            System.out.println("numberOfFaces: " + numberOfFaces);

            long numberOfMipmapLevels = ByteBuffer.wrap(inputStream.readNBytes(4)).order(byteOrder).getInt();
            if (numberOfMipmapLevels < 0) {
                numberOfMipmapLevels += (1L << 32);
                // numberOfMipmapLevels ^= (0xFFFFFFFFL);
            }
            System.out.println("numberOfMipmapLevels: " + numberOfMipmapLevels);






            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void writeKTXImage(Path ktxImagePath, BufferedImage bufferedImage) {

    }

}
