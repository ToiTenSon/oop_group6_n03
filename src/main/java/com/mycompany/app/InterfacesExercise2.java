// Đường dẫn: main/java/com/mycompany/app/CollisionTest.java

import net.mindview.simple.*; // Giả sử có một lớp Vector trong gói này
import java.util.*;            // Gói này cũng chứa một lớp Vector

public class CollisionTest {
    public static void main(String[] args) {
        // Đây sẽ gây ra lỗi va chạm vì không biết sử dụng lớp Vector nào
        // Vector v = new Vector(); // Gây ra lỗi biên dịch

        // Để giải quyết, bạn phải chỉ rõ lớp Vector nào cần sử dụng
        java.util.Vector<String> v1 = new java.util.Vector<>();
        v1.add("Java");
        System.out.println("java.util.Vector: " + v1);

        // Hoặc nếu có một lớp Vector trong gói net.mindview.simple, bạn có thể làm như sau:
        // net.mindview.simple.Vector v2 = new net.mindview.simple.Vector();
        // System.out.println("net.mindview.simple.Vector: " + v2);
    }
}
