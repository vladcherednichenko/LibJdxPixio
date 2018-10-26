package test.pixiolibgdx.game;


import java.util.HashMap;

public class PixioHelper {

    public static int[] allColors = {248, 247, 246, 245, 244, 243, 242, 241, 249, 250, 251, 252, 253, 255, 240, 254, 238};

    public static final HashMap<Integer, Integer> indexToColorCodeMap = new HashMap<Integer, Integer>() {{
        put(0, 248);
        put(1, 247);
        put(2, 246);
        put(3, 245);
        put(4, 244);
        put(5, 243);
        put(6, 242);
        put(7, 241);
        put(8, 249);
        put(9, 250);
        put(10, 251);
        put(11, 252);
        put(12, 253);
        put(13, 240);
        put(14, 255);
        put(15, 254);

    }};

    public static final HashMap<Integer, Integer> colorCodeToIndexMap = new HashMap<Integer, Integer>() {{
        put(248, 0);
        put(247, 1);
        put(246, 2);
        put(245, 3);
        put(244, 4);
        put(243, 5);
        put(242, 6);
        put(241, 7);
        put(249, 8);
        put(250, 9);
        put(251, 10);
        put(252, 11);
        put(253, 12);
        put(240, 13);
        put(255, 14);
        put(254, 15);
        put(232, 15);
        put(238, 13);

    }};

    public static final HashMap<Integer, String> colorCodeToHex = new HashMap<Integer, String>() {
        {
            put(248, "#E4002B"); //red
            put(247, "#FF8200"); //orange
            put(246, "#FEDB00"); //yellow
            put(245, "#62b013"); //light green
            put(244, "#00843D"); //green
            put(243, "#00b8b8"); //turquoise
            put(242, "#41B6E6"); //light blue
            put(241, "#003087"); //blue
            put(249, "#753BBD"); //violet
            put(250, "#F57EB6"); //pink
            put(251, "#FCD299"); //tan
            put(252, "#C88242"); //light brown
            put(253, "#693F23"); //brown
            put(255, "#A2AAAD"); //grey
            put(240, "#333333"); //black
            put(254, "#ffffff"); //white
            put(238, "#333333"); //black
        }
    };

    public static final HashMap<String, Integer> hexToColorCode = new HashMap<String, Integer>() {
        {
            put("#E4002B", 248); //red
            put("#FF8200", 247); //orange
            put("#FEDB00", 246); //yellow
            put("#62b013", 245); //light green
            put("#00843D", 244); //green
            put("#00b8b8", 243); //turquoise
            put("#41B6E6", 242); //light blue
            put("#003087", 241); //blue
            put("#753BBD", 249); //violet
            put("#F57EB6", 250); //pink
            put("#FCD299", 251); //tan
            put("#C88242", 252); //light brown
            put("#693F23", 253); //brown
            put("#A2AAAD", 255); //grey
            put("#333333", 240); //black
            put("#ffffff", 254); //white
        }
    };


    public static final HashMap<Integer, String> colorCodeToFileName = new HashMap<Integer, String>() {{
        put(248, "pixio_color_cube_11");
        put(247, "pixio_color_cube_12");
        put(246, "pixio_color_cube_13");
        put(245, "pixio_color_cube_14");
        put(244, "pixio_color_cube_15");
        put(243, "pixio_color_cube_16");
        put(242, "pixio_color_cube_07");
        put(241, "pixio_color_cube_08");
        put(249, "pixio_color_cube_09");
        put(250, "pixio_color_cube_10");
        put(251, "pixio_color_cube_04");
        put(252, "pixio_color_cube_05");
        put(253, "pixio_color_cube_06");
        put(255, "pixio_color_cube_02");
        put(240, "pixio_color_cube_01");
        put(254, "pixio_color_cube_03");
        put(238, "pixio_color_cube_01");

    }};

    public static final HashMap<Integer, String> colorCodeToColor = new HashMap<Integer, String>() {{
        put(248, "Red");
        put(247, "Orange");
        put(246, "Yellow");
        put(245, "Light Green");
        put(244, "Green");
        put(243, "Turquoise");
        put(242, "Light Blue");
        put(241, "Blue");
        put(249, "Violet");
        put(250, "Pink");
        put(251, "Tan");
        put(252, "Light Brown");
        put(253, "Brown");
        put(240, "Black");
        put(255, "Grey");
        put(254, "White");
        put(232, "White");
        put(238, "Black");

    }};

    public enum PixioSetType {
        pixio50,
        pixio100,
        pixio200,
        pixio400,
        pixio800,
        pixio1600,
        pixio3200,
        pixio800CUSTOM,
        pixioALIEN,
        pixioFLAMINGO,
        pixioBOT,
        pixioFLOWER,
        pixioMAN,
        pixioORANGEANIMAL,
        pixioHAPPYFAMILY,
        pixioBRIGHTANIMALS,
        pixioBLACKANDWHITE,
        pixioMINIDINOS,
        pixioMINIMONSTERS,
        pixioMINIOCEAN,
        pixioMINISAFARY,
        undefined

    }


}