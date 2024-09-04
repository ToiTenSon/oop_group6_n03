// File: AccessTest.java

// Định nghĩa gói
package access.local;

// Lớp TestAccess
public class TestAccess {
    public void display() {
        System.out.println("This is TestAccess in the access.local package.");
    }
}

// Lớp TestLocal
public class TestLocal {
    public void display() {
        System.out.println("This is TestLocal in the access.local package.");
    }
}

// Lớp Main kiểm tra các lớp trên
public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng của TestAccess và gọi phương thức display
        TestAccess access = new TestAccess();
        access.display();
        
        // Tạo đối tượng của TestLocal và gọi phương thức display
        TestLocal local = new TestLocal();
        local.display();
    }
}
