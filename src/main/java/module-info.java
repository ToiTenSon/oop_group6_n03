module com.example.managerstudent {
    requires javafx.controls;
    requires javafx.fxml;


    // Cho phép mô-đun javafx.graphics truy cập package View để khởi động ứng dụng
    exports com.example.managerstudent.View;

    // Mở package View cho javafx.fxml để FXML loader có thể sử dụng reflection
    opens com.example.managerstudent.View to javafx.fxml;
    opens com.example.managerstudent.Controller to javafx.fxml;

    exports com.example.managerstudent;
    exports com.example.managerstudent.Controller;

    opens com.example.managerstudent to javafx.fxml;
}