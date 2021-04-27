package jdbc;

import model.DishStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DishStatusMapper implements RowMapper<DishStatus> {
    @Override
    public DishStatus mapRow(ResultSet resultSet, int i) throws SQLException {
        DishStatus dishStatus;

        if(resultSet.getString("STATUS").equals(DishStatus.QUEUED)) {
            dishStatus = DishStatus.QUEUED;
        }
        else {
            if (resultSet.getString("STATUS").equals(DishStatus.COOKING)) {
                dishStatus = DishStatus.COOKING;
            }
            else {
                dishStatus = DishStatus.READY;
            }
        }

        return dishStatus;
    }
}
