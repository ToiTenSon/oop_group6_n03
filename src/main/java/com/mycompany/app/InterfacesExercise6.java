// Exercise6.java

// Lớp chứa dữ liệu protected
class ProtectedDataClass {
    // Dữ liệu protected
    protected String protectedData = "This is protected data";
    
    // Phương thức để hiển thị dữ liệu
    protected void displayProtectedData() {
        System.out.println("Protected Data: " + protectedData);
    }
}

// Lớp thứ hai trong cùng một file thao tác với dữ liệu protected
public class DataManipulator {
    // Phương thức thao tác với dữ liệu protected
    public void manipulateData() {
        // Tạo đối tượng của lớp ProtectedDataClass
        ProtectedDataClass dataClass = new ProtectedDataClass();
        
        // Truy cập và thao tác với dữ liệu protected
        System.out.println("Accessing protected data from DataManipulator:");
        System.out.println("Protected Data: " + dataClass.protectedData);
        dataClass.displayProtectedData();
    }
    
    // Phương thức main để chạy chương trình
    public static void main(String[] args) {
        DataManipulator manipulator = new DataManipulator();
        manipulator.manipulateData();
    }
}
