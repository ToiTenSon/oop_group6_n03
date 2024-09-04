// File: ConnectionExample.java

// Lớp Connection quản lý các kết nối
class Connection {
    private static int count = 0; // Đếm số đối tượng đã tạo
    
    public Connection() {
        count++;
    }
    
    public void use() {
        System.out.println("Using connection " + count);
    }
}

// Lớp ConnectionManager quản lý các đối tượng Connection
class ConnectionManager {
    private static final int MAX_CONNECTIONS = 5; // Số lượng kết nối tối đa
    private static Connection[] connections = new Connection[MAX_CONNECTIONS];
    private static boolean[] used = new boolean[MAX_CONNECTIONS]; // Mảng đánh dấu kết nối đã được sử dụng

    static {
        // Khởi tạo tất cả các đối tượng Connection
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            connections[i] = new Connection();
            used[i] = false;
        }
    }

    public static Connection getConnection() {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            if (!used[i]) {
                used[i] = true;
                return connections[i];
            }
        }
        return null; // Trả về null nếu không còn kết nối nào
    }
    
    public static void releaseConnection(Connection connection) {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            if (connections[i] == connection) {
                used[i] = false; // Đánh dấu kết nối là chưa được sử dụng
                return;
            }
        }
    }
}

// Lớp Main để kiểm tra hoạt động của các lớp
public class ConnectionExample {
    public static void main(String[] args) {
        // Lấy kết nối từ ConnectionManager
        Connection conn1 = ConnectionManager.getConnection();
        if (conn1 != null) {
            conn1.use();
        }

        // Lấy thêm kết nối và sử dụng
        Connection conn2 = ConnectionManager.getConnection();
        if (conn2 != null) {
            conn2.use();
        }
        
        // Giải phóng kết nối
        ConnectionManager.releaseConnection(conn1);

        // Lấy kết nối khác
        Connection conn3 = ConnectionManager.getConnection();
        if (conn3 != null) {
            conn3.use();
        }
        
        // Thử lấy thêm kết nối khi đã hết
        for (int i = 0; i < 5; i++) {
            Connection conn = ConnectionManager.getConnection();
            if (conn == null) {
                System.out.println("No more connections available.");
            } else {
                conn.use();
            }
        }
    }
}
