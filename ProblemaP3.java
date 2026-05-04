import java.util.Scanner;

public class ProblemaP3 {

    static int[][] memo;
    static String s1, s2;
 
    // Recursive con memoización — solo calcula longitudes
    public static int lcsRec(int i, int j) {
        if (i == 0 || j == 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            memo[i][j] = 1 + lcsRec(i - 1, j - 1);
        } else {
            memo[i][j] = Math.max(lcsRec(i - 1, j), lcsRec(i, j - 1));
        }
        return memo[i][j];
    }
 
    // Backtracking sobre la tabla memo para reconstruir el string
    public static String backtrack(int i, int j) {
        if (i == 0 || j == 0) return "";
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return backtrack(i - 1, j - 1) + s1.charAt(i - 1);
        } else {
            if (lcsRec(i - 1, j) >= lcsRec(i, j - 1)) {
                return backtrack(i - 1, j);
            } else {
                return backtrack(i, j - 1);
            }
        }
    }
 
    public static String lcs(String a, String b) {
        s1 = a;
        s2 = b;
        int m = a.length();
        int n = b.length();
        memo = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++)
                memo[i][j] = -1;
        lcsRec(m, n);
        return backtrack(m, n);
    }
 
    public static String resolverLCS(String[] cadenas) {
        String resultado = cadenas[0];
        for (int i = 1; i < cadenas.length; i++) {
            resultado = lcs(resultado, cadenas[i]);
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
