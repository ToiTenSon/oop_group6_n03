// Đường dẫn: main/java/com/mycompany/mypackage/MyClass.java
package com.mycompany.mypackage;

public class MyClass {
    public void displayMessage() {
        System.out.println("Hello from MyClass inside com.mycompany.mypackage!");
    }
}

// Đường dẫn: main/java/com/mycompany/app/TestClass.java
package com.mycompany.app;

import com.mycompany.mypackage.MyClass;

public class TestClass {
    public static void main(String[] args) {
        MyClass myClassInstance = new MyClass();  // Tạo thể hiện của MyClass
        myClassInstance.displayMessage();  // Gọi phương thức của MyClass
    }
}
