import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import org.apache.commons.lang3.RandomStringUtils;

public class WeakRandomCookies extends HttpServlet {
    HttpServletResponse response;

    public void doGet() {
        Random r = new Random();

        int c = r.nextInt();
        // BAD: The cookie value may be predictable.
        Cookie cookie = new Cookie("name", Integer.toString(c));
        response.addCookie(cookie); // $hasWeakRandomFlow

        int c2 = r.nextInt();
        // BAD: The cookie value may be predictable.
        Cookie cookie2 = new Cookie("name" + c2, "value");
        response.addCookie(cookie2); // $hasWeakRandomFlow

        byte[] bytes = new byte[16];
        r.nextBytes(bytes);
        // BAD: The cookie value may be predictable.
        Cookie cookie3 = new Cookie("name", new String(bytes));
        response.addCookie(cookie3); // $hasWeakRandomFlow

        SecureRandom sr = new SecureRandom();

        byte[] bytes2 = new byte[16];
        sr.nextBytes(bytes2);
        // GOOD: The cookie value is unpredictable.
        Cookie cookie4 = new Cookie("name", new String(bytes2));
        response.addCookie(cookie4);

        ThreadLocalRandom tlr = ThreadLocalRandom.current();

        Cookie cookie5 = new Cookie("name", Integer.toString(tlr.nextInt()));
        response.addCookie(cookie5); // $hasWeakRandomFlow

        Cookie cookie6 = new Cookie("name", RandomStringUtils.random(10));
        response.addCookie(cookie6); // $hasWeakRandomFlow

        Cookie cookie7 = new Cookie("name", RandomStringUtils.randomAscii(10));
        response.addCookie(cookie7); // $hasWeakRandomFlow

        long c3 = r.nextLong();
        // BAD: The cookie value may be predictable.
        Cookie cookie8 = new Cookie("name", Long.toString(c3 * 5));
        response.addCookie(cookie8); // $hasWeakRandomFlow

        double c4 = Math.random();
        // BAD: The cookie value may be predictable.
        Cookie cookie9 = new Cookie("name", Double.toString(c4));
        response.addCookie(cookie9); // $hasWeakRandomFlow

        double c5 = Math.random();
        // BAD: The cookie value may be predictable.
        Cookie cookie10 = new Cookie("name", Double.toString(++c5));
        response.addCookie(cookie10); // $hasWeakRandomFlow
    }
}
