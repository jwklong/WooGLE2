package com.woogleFX.gameData.animation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class AnimBinReader {

    // What ARE these?
    public static void attemptToRead(Path path) {

        try {

            ByteArrayInputStream input = new ByteArrayInputStream(Files.readAllBytes(path));

            int int1 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 1: " + int1);

            int int2 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 2: " + int2);

            int int3 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 3: " + int3);

            int int4 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 4: " + int4);

            int int5 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 5: " + int5);

            int int6 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 6: " + int6);

            int int7 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 7: " + int7);

            int int8 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 8: " + int8);

            int int9 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 9: " + int9);

            int int10 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 10: " + int10);

            int int11 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 11: " + int11);

            int int12 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 12: " + int12);

            int int13 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 13: " + int13);

            int int14 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 14: " + int14);

            int int15 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 15: " + int15);

            int int16 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 16: " + int16);

            int int17 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 17: " + int17);

            int int18 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 18: " + int18);

            int int19 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 19: " + int19);

            int int20 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 20: " + int20);

            int int21 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 21: " + int21);

            int int22 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 22: " + int22);

            int int23 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 23: " + int23);

            int int24 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 24: " + int24);

            int int25 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 25: " + int25);

            int int26 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 26: " + int26);

            int int27 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 27: " + int27);

            int int28 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 28: " + int28);

            int int29 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 29: " + int29);

            int int30 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 30: " + int30);

            int int31 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 31: " + int31);

            int int32 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 32: " + int32);

            int int33 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 33: " + int33);

            int int34 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 34: " + int34);

            int int35 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 35: " + int35);

            int int36 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 36: " + int36);

            int int37 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 37: " + int37);

            int int38 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 38: " + int38);

            int int39 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 39: " + int39);

            int int40 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 40: " + int40);

            int int41 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 41: " + int41);

            int int42 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 42: " + int42);

            int int43 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 43: " + int43);

            int int44 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 44: " + int44);

            int int45 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 45: " + int45);

            int int46 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 46: " + int46);

            int int47 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 47: " + int47);

            int int48 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 48: " + int48);

            int int49 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 49: " + int49);

            int int50 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 50: " + int50);

            int int51 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 51: " + int51);

            int int52 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 52: " + int52);

            int int53 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 53: " + int53);

            int int54 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Int 54: " + int54);

            int int55 = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("FPS: " + int55);

            // (4) - (2) = (1) * 12
            System.out.println("2 to 4 - groups of 12");
            for (int i = 0; i < int1; i++) {
                System.out.print("{ ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                System.out.print("}  ");
            }
            System.out.println();

            // (6) - (4) = (3) * 24
            System.out.println("4 to 6 - groups of 24");
            for (int i = 0; i < int3; i++) {
                System.out.print("{ ");
                for (int j = 0; j < 6; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                System.out.print("}  ");
            }
            System.out.println();

            // (8) - (6) = (5) * 12
            System.out.println("6 to 8 - groups of 12");
            for (int i = 0; i < int5; i++) {
                System.out.print("{ ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                System.out.print("}  ");
            }
            System.out.println();

            // (10) - (8) = (7) * 12
            System.out.println("8 to 10 - groups of 12");
            for (int i = 0; i < int7; i++) {
                System.out.print("{ ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                System.out.print("}  ");
            }
            System.out.println();

            // (12) - (10) = (9) * 48
            System.out.println("10 to 12 - groups of 48 (Keyframes)");
            for (int i = 0; i < int9; i++) {
                System.out.print("{ ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                System.out.print(" angle1:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" angle2:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" scaleX:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" scaleY:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print(" color:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                System.out.print(" ???:" + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                System.out.print("}  ");
            }
            System.out.println();

            // (14) - (12) = (11) * 44
            System.out.println("12 to 14 - groups of 44");
            for (int i = 0; i < int11; i++) {
                System.out.print("{ ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                for (int j = 3; j < 5; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                }
                for (int j = 5; j < 7; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                for (int j = 7; j < 9; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0) + " ");
                }
                for (int j = 9; j < 11; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                System.out.print("}  ");
            }
            System.out.println();

            // (16) - (14) = (13) * 20

            // (18) - (16) = (15) * 8

            // (20) - (18) = (17) * 28

            // (22) - (20) = (19) * 12





            // (40) - (38) = (37) *

            // (42) - (40) = (39) * 4
            System.out.println("40 to 42 - groups of 4");
            for (int i = 0; i < int39; i++) {
                System.out.print("{ ");
                System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                System.out.print("}  ");
            }
            System.out.println();

            // (44) - (42) = (41) *

            // (46) - (44) = (43) *

            // (48) - (46) = (45) * 4
            System.out.println("46 to 48 - groups of 4");
            for (int i = 0; i < int45; i++) {
                System.out.print("{ ");
                System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                System.out.print("}  ");
            }
            System.out.println();

            // (50) - (48) = (47) * 4
            System.out.println("48 to 50 - groups of 4");
            for (int i = 0; i < int47; i++) {
                System.out.print("{ ");
                System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                System.out.print("}  ");
            }
            System.out.println();

            // (52) - (50) = (49) * 8
            System.out.println("50 to 52 - groups of 8");
            for (int i = 0; i < int49; i++) {
                System.out.print("{ ");
                for (int j = 0; j < 2; j++) {
                    System.out.print(ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + " ");
                }
                System.out.print("}  ");
            }
            System.out.println();

            /*

            int[] data1 = new int[(int4 - int2) / 4];
            for (int i = 0; i < int4 - int2; i += 4) data1[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 2, 3");
            System.out.println(Arrays.toString(data1));

            System.out.println("Reading data indicated by int 4, 5");

            for (int i = 0; i < 2; i++) {
                System.out.print("(some number?: " + ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0) + "}, ");
            }
            for (int i = 0; i < int6 - int4 - 4; i += 24) {

                int A = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int B = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int C = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int D = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int E = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int F = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);

                System.out.print(A + ": {" + B + ", " + C + ", " + D + ", " + E + ", " + F + "}, ");

            }

            System.out.println();

            int[] data3 = new int[(int8 - int6) / 4];
            for (int i = 0; i < int8 - int6; i += 4) data3[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 6, 7");
            System.out.println(Arrays.toString(data3));

            int[] data4 = new int[(int10 - int8) / 4];
            for (int i = 0; i < int10 - int8; i += 4) data4[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 8, 9");
            System.out.println(Arrays.toString(data4));

            System.out.println("Reading data indicated by int 10, 11");

            for (int j = 0; j < int9; j++) {

                int A = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int B = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int C = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                int D = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int E = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int F = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int G = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int H = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int I = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int J = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int K = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int L = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int M = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int N = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int O = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
                // int P = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);

                // System.out.print("{ " + A + " " + B + " " + C + " " + D + " " + E + " " + F + " " + G + " " + H + " " + I + " " + J + " " + K + " " + L + " " + M + " " + N + " " + O + " " + P + " }, ");
                System.out.print("{ " + A + " " + B + " " + C + " " + D + " }, ");

            }

            System.out.println();

            float[] data6 = new float[(int14 - int12) / 4];
            for (int i = 0; i < int14 - int12; i += 4) data6[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0);
            System.out.println("Reading data indicated by int 12, 13");
            System.out.println(Arrays.toString(data6));

            int[] data7 = new int[(int16 - int14) / 4];
            for (int i = 0; i < int16 - int14; i += 4) data7[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 14, 15");
            System.out.println(Arrays.toString(data7));

            int[] data8 = new int[(int18 - int16) / 4];
            for (int i = 0; i < int18 - int16; i += 4) data8[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 16, 17");
            System.out.println(Arrays.toString(data8));

            int[] data9 = new int[(int20 - int18) / 4];
            for (int i = 0; i < int20 - int18; i += 4) data9[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 18, 19");
            System.out.println(Arrays.toString(data9));

            int[] data10 = new int[(int22 - int20) / 4];
            for (int i = 0; i < int22 - int20; i += 4) data10[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 20, 21");
            System.out.println(Arrays.toString(data10));

            int[] data11 = new int[(int24 - int22) / 4];
            for (int i = 0; i < int24 - int22; i += 4) data11[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 22, 23");
            System.out.println(Arrays.toString(data11));

            int[] data12 = new int[(int26 - int24) / 4];
            for (int i = 0; i < int26 - int24; i += 4) data12[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 24, 25");
            System.out.println(Arrays.toString(data12));

            int[] data13 = new int[(int28 - int26) / 4];
            for (int i = 0; i < int28 - int26; i += 4) data13[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 26, 27");
            System.out.println(Arrays.toString(data13));

            int[] data14 = new int[(int30 - int28) / 4];
            for (int i = 0; i < int30 - int28; i += 4) data14[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 28, 29");
            System.out.println(Arrays.toString(data14));

            int[] data15 = new int[(int32 - int30) / 4];
            for (int i = 0; i < int32 - int30; i += 4) data15[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 30, 31");
            System.out.println(Arrays.toString(data15));

            int[] data16 = new int[(int34 - int32) / 4];
            for (int i = 0; i < int34 - int32; i += 4) data16[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 32, 33");
            System.out.println(Arrays.toString(data16));

            int[] data17 = new int[(int36 - int34) / 4];
            for (int i = 0; i < int36 - int34; i += 4) data17[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 34, 35");
            System.out.println(Arrays.toString(data17));

            int[] data18 = new int[(int38 - int36) / 4];
            for (int i = 0; i < int38 - int36; i += 4) data18[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 36, 37");
            System.out.println(Arrays.toString(data18));

            int[] data19 = new int[(int40 - int38) / 4];
            for (int i = 0; i < int40 - int38; i += 4) data19[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 38, 39");
            System.out.println(Arrays.toString(data19));

            int[] data20 = new int[(int42 - int40) / 4];
            for (int i = 0; i < int42 - int40; i += 4) data20[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 40, 41");
            System.out.println(Arrays.toString(data20));

            int[] data21 = new int[(int44 - int42) / 4];
            for (int i = 0; i < int44 - int42; i += 4) data21[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 42, 43");
            System.out.println(Arrays.toString(data21));

            int[] data22 = new int[(int46 - int44) / 4];
            for (int i = 0; i < int46 - int44; i += 4) data22[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 44, 45");
            System.out.println(Arrays.toString(data22));

            int[] data23 = new int[(int48 - int46) / 4];
            for (int i = 0; i < int48 - int46; i += 4) data23[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 46, 47");
            System.out.println(Arrays.toString(data23));

            int[] data24 = new int[(int50 - int48) / 4];
            for (int i = 0; i < int50 - int48; i += 4) data24[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 48, 49");
            System.out.println(Arrays.toString(data24));

            int[] data25 = new int[(int52 - int50) / 4];
            for (int i = 0; i < int52 - int50; i += 4) data25[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 50, 51");
            System.out.println(Arrays.toString(data25));

            int[] data26 = new int[int53 / 4];
            for (int i = 0; i < int53 && input.available() >= 4; i += 4) data26[i / 4] = ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
            System.out.println("Reading data indicated by int 52, 53");
            System.out.println(Arrays.toString(data26));

             */


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
