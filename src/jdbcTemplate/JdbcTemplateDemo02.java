package jdbcTemplate;

import domain.Account;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo02 {
    //每次都要调用 获取 JdbcTemplate对象
    private JdbcTemplate template;

    {
        try {
            template = new JdbcTemplate(JDBCUtils.getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        //修改一条记录
        String sql = "update emp set salary = 8888 where id = 1001";
        int i = template.update(sql);
        System.out.println(i);
    }

    @Test
    public void test2() {
        //添加一条记录
        String sql = "insert into account(name,balance) values (?,?)";
        int i = template.update(sql, "郝永亮", 8888);
        System.out.println(i);
    }

    @Test
    public void test3() {
        //删除一条数据
        String sql = "delete from account where id = ?";
        int update = template.update(sql, 5);
        System.out.println(update);
    }

    @Test
    public void test4() {
        //4.查询id为13的记录，将其封装为Map集合
        String sql = "select * from account where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 8);
        System.out.println(map);
    }

    @Test
    public void test5() {
//        5. 查询所有记录，将其封装为List
        String sql = "select * from account";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> lists : list) {
            System.out.println(lists);
        }
    }

    @Test
    public void test6() {
        //6. 查询所有记录，将其封装为Emp对象的List集合
        String sql = "select * from account";
        List<Account> list = template.query(sql, new RowMapper<Account>() {

            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                account.setId(id);
                account.setName(name);
                account.setBalance(balance);

                return account;
            }
        });

        for (Account account : list) {
            System.out.println(account);
        }

    }

    @Test
    public void test7() {
        String sql = "select * from account";
        List<Account> list = template.query(sql, new BeanPropertyRowMapper<>(Account.class));
        for (Account account : list) {
            System.out.println(account);
        }
    }

    @Test
    public void test8() {
        String sql = "select count(id) from account";
        Long aLong = template.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }
}
