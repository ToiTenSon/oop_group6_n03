// File: WidgetDemo.java
// Thư mục: src (hoặc thư mục gốc)

package access;

public class Widget {
    public void display() {
        System.out.println("Widget display method.");
    }
}

// Lớp WidgetTest không thuộc gói access
// Nằm trong cùng thư mục hoặc một thư mục khác
public class WidgetTest {
    public static void main(String[] args) {
        // Tạo đối tượng của lớp Widget
        access.Widget widget = new access.Widget();
        
        // Gọi phương thức của lớp Widget
        widget.display();
    }
}
