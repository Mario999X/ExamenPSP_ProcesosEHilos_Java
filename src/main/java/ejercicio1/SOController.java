package ejercicio1;

public class SOController {
    public static Boolean init() {
        boolean isLinux = false;

        String so = System.getProperty("os.name").toLowerCase();
        if (so.contains("linux") || so.contains("mac")) {
            isLinux = true;
        }
        return isLinux;
    }
}
