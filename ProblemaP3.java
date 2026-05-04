import java.util.Scanner;

public class ProblemaP3 {

    public static int[] lcsLength (String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int [] prev = new int [n + 1];
        for (int i = 1; i <= m; i++) {
            int [] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        return prev;
    }

    public static String hirschbergRec(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        if (s1.length() == 1) {
            if (s2.indexOf(s1.charAt(0)) != -1) {
                return String.valueOf(s1.charAt(0));
            } else {
                return "";
            }
        }
        if (s2.length() == 1) {
            if (s1.indexOf(s2.charAt(0)) != -1) {
                return String.valueOf(s2.charAt(0));
            } else {
                return "";
            }
        }

        int m = s1.length() / 2;
        int[] l1 = lcsLength(s1.substring(0, m), s2);
        int[] l2 = lcsLength(
            new StringBuilder(s1.substring(m)).reverse().toString(),
            new StringBuilder(s2).reverse().toString()
        );

        int split = 0;
        int best = -1;
        int n = s2.length();
        for (int j = 0; j <= n; j++) {
            int val = l1[j] + l2[n - j];
            if (val > best) {
                best = val;
                split = j;
            }
        }

        return hirschbergRec(s1.substring(0, m), s2.substring(0, split))
            + hirschbergRec(s1.substring(m), s2.substring(split));
    }
 
    public static String resolverLCS(String[] cadenas) {
        String resultado = cadenas[0];
        for (int i = 1; i < cadenas.length; i++) {
            resultado = hirschbergRec(resultado, cadenas[i]);
            if (resultado.isEmpty()) break;
        }
        return resultado;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String[] cadenas = new String[n];
            for (int i = 0; i < n; i++) {
                cadenas[i] = sc.next();
            }
            System.out.println(resolverLCS(cadenas) + "*");
        }
        sc.close();
    }
}
