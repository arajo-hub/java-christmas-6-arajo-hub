package christmas.view;

/**
 * 에러를 출력하는 클래스
 */
public class ErrorOutputView {

    public static final String ERROR_HEADER = "[ERROR]";

    /**
     * 에러를 형식에 맞춰 출력한다.
     * @param message 에러메시지
     */
    public static void printError(String message) {
        System.out.println(String.format("%s %s", ERROR_HEADER, message));
    }

}
