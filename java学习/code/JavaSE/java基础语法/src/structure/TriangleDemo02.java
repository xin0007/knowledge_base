package structure;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class TriangleDemo02 {
    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            for (int i1 = 6; i1 > i; i1--) {
                System.out.print(" ");
            }
            for (int i1 = 0; i1 < i; i1++) {
                System.out.print("*");
            }

            for (int i1 = 1; i1 < i; i1++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
