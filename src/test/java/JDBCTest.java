import com.lusiwei.dao.impl.UserDaoImpl;
import com.lusiwei.pojo.User;
import com.lusiwei.util.JDBCUtils;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created  by lusiwei on 2018/10/11
 */
public class JDBCTest {
    @Test
    public void TestJDBC(){
        String sql = "select * from tab_user where username=?";
        try {
            User user= new UserDaoImpl().querySingle(JDBCUtils.getConnection(), User.class, sql, "root123");
            System.out.println("---lsw---user值=" + user + "," + "当前类=JDBCTest.TestJDBC()");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
