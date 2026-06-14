public class ConfirmationGenerator {

    private static int counter = 1000;

    public static int generateConfirmationNumber() {
        return counter++;
    }
}