import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {

        try {
            new ProcessBuilder(
                "cmd", "/c", "start", "cmd", "/k",
                "java SnakeLadderGame"
            ).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}