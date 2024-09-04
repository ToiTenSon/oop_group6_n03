// Gói debug: hiển thị thông điệp gỡ lỗi
package com.mycompany.app.debug;

public class Debug {
    public static void debug(String message) {
        System.out.println("Debug: " + message);
    }
}

// Gói debugoff: không làm gì cả
package com.mycompany.app.debugoff;

public class Debug {
    public static void debug(String message) {
        // Không làm gì cả
    }
}

// Chương trình kiểm tra
package com.mycompany.app;

// Chọn gói debug hoặc debugoff bằng cách thay đổi dòng import dưới đây
import static com.mycompany.app.debug.Debug.*; 
// import static com.mycompany.app.debugoff.Debug.*;

public class TestDebug {
    public static void main(String[] args) {
        debug("This is a test message.");
    }
}
