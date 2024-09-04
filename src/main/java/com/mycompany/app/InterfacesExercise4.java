// BaseClass.java
package com.example;

public class BaseClass {
    // Phương thức protected
    protected void protectedMethod() {
        System.out.println("Protected method in BaseClass");
    }
}

// SamePackageClass.java
package com.example;

public class SamePackageClass {
    public static void main(String[] args) {
        BaseClass base = new BaseClass();
        
        // Truy cập phương thức protected từ cùng một gói
        base.protectedMethod(); // Được phép
    }
}

// DifferentPackageClass.java
package com.other;

import com.example.BaseClass;

public class DifferentPackageClass extends BaseClass {
    public static void main(String[] args) {
        DifferentPackageClass obj = new DifferentPackageClass();
        
        // Truy cập phương thức protected từ một gói khác
        obj.protectedMethod(); // Được phép vì nó là lớp kế thừa
    }
}

// AccessTest.java (kiểm tra từ bên ngoài gói)
package com.other;

import com.example.BaseClass;

public class AccessTest {
    public static void main(String[] args) {
        BaseClass base = new BaseClass();
        
        // Truy cập phương thức protected từ một gói khác và không phải lớp kế thừa
        // base.protectedMethod(); // Lỗi biên dịch, phương thức protected không thể truy cập từ bên ngoài gói
    }
}
