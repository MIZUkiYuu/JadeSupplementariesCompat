package me.mizukiyuu.jadesupplementariescompat.utils;

public enum ColorMap {
    WHITE(0, 0xf9fffe),
    ORANGE(1, 0xf9801d),
    MAGENTA(2, 0xc74ebd),
    LIGHT_BLUE(3, 0x3ab3da),
    YELLOW(4, 0xfed83d),
    LIME(5, 0x80c71f),
    PINK(6, 0xf38baa),
    GRAY(7, 0x474f52),
    LIGHT_GRAY(8, 0x9d9d97),
    CYAN(9, 0x169c9c),
    PURPLE(10, 0x8932b8),
    BLUE(11, 0x3c44aa),
    BROWN(12, 0x835432),
    GREEN(13, 0x5e7c16),
    RED(14, 0xb02e26),
    BLACK(15, 0x1d1d21);

    final int id;
    final int color;

    public static final int DEFAULT_ALPHA = 0xff;
    public static final ColorMap[] COLOR_MAPS = ColorMap.values();
    public static final int COUNT = COLOR_MAPS.length;
    private ColorMap(int id, int color){

        this.id = id;
        this.color = color;
    }
    public int getId() { return id; }
    public int getColor() { return color; }

    public int withAlpha(){
        return withAlpha(DEFAULT_ALPHA);
    }

    /**
     *
     * @param alpha a HEX int number
     * @return color with specified alpha
     */
    public int withAlpha(int alpha){
        return this.color | (alpha * 0x1000000);
    }

    public static ColorMap getColorById(int id){
        return COLOR_MAPS[id];
    }
}
