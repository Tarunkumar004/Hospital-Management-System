package minihospital;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DbUtils {
public static TableModel resultSetToTableModel(ResultSet resultSet) throws SQLException {
    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
    int columnCount = metaData.getColumnCount();
    String[] columnNames = new String[columnCount];
    for (int i = 0; i < columnCount; i++) {
        columnNames[i] = metaData.getColumnName(i + 1);
    }
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    while (resultSet.next()) {
        Object[] row = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
            row[i] = resultSet.getObject(i + 1);
        }
        model.addRow(row);
    }
    return model;
}
}
