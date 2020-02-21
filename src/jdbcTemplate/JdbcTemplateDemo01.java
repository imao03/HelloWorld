package jdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JdbcTemplateDemo01 {
    public static void main(String[] args) throws Exception {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        String sql = "update account set balance = 8888 where id = ?";

        int count = template.update(sql, 1);
        System.out.println(count);
    }
}
