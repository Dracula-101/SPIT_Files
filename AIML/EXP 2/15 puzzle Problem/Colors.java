public class Colors {
    public String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public String ANSI_RED_BACKGROUND = "\u001B[41m";
    public String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public String ANSI_RESET = "\u001B[0m";

    Colors() {
        System.out.println("Colors class created");
    }

    String red(String input) {
        return ANSI_RED_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String green(String input) {
        return ANSI_GREEN_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String yellow(String input) {
        return ANSI_YELLOW_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String blue(String input) {
        return ANSI_BLUE_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String purple(String input) {
        return ANSI_PURPLE_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String cyan(String input) {
        return ANSI_CYAN_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String white(String input) {
        return ANSI_WHITE_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String black(String input) {
        return ANSI_BLACK_BACKGROUND + input + ANSI_BLACK_BACKGROUND;
    }

    String reset(String input) {
        return ANSI_RESET + input + ANSI_RESET;
    }

    String color(String input, String color) {
        switch (color) {
            case "red":
                return red(input);
            case "green":
                return green(input);
            case "yellow":
                return yellow(input);
            case "blue":
                return blue(input);
            case "purple":
                return purple(input);
            case "cyan":
                return cyan(input);
            case "white":
                return white(input);
            case "black":
                return black(input);
            default:
                return reset(input);
        }
    }
}
