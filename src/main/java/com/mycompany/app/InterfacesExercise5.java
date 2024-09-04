// MyClass.java
package com.example; // Đảm bảo gói đúng nếu bạn đặt tên gói khác

public class MyClass {
    // Các thành viên của lớp với các mức độ truy cập khác nhau
    public int publicField = 1;
    private int privateField = 2;
    protected int protectedField = 3;
    int packageField = 4; // Package-access (mặc định)

    // Phương thức public
    public void publicMethod() {
        System.out.println("Public method");
    }

    // Phương thức private
    private void privateMethod() {
        System.out.println("Private method");
    }

    // Phương thức protected
    protected void protectedMethod() {
        System.out.println("Protected method");
    }

    // Phương thức package-access (mặc định)
    void packageMethod() {
        System.out.println("Package-access method");
    }
}

// AccessTest.java
package com.example; // Đảm bảo gói đúng nếu bạn đặt tên gói khác

public class AccessTest {
    public static void main(String[] args) {
        MyClass obj = new MyClass();

        // Truy cập các thành viên của lớp MyClass
        System.out.println("Public field: " + obj.publicField); // Được phép
        // System.out.println("Private field: " + obj.privateField); // Lỗi biên dịch
        System.out.println("Protected field: " + obj.protectedField); // Được phép vì cùng gói
        System.out.println("Package-access field: " + obj.packageField); // Được phép vì cùng gói

        obj.publicMethod(); // Được phép
        // obj.privateMethod(); // Lỗi biên dịch
        obj.protectedMethod(); // Được phép vì cùng gói
        obj.packageMethod(); // Được phép vì cùng gói
    }
}
