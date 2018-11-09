package com.jdbc.application.dao;
import com.jdbc.application.model.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
/** @author Artem Bekh
 * This class contains Template Method. Method extract
 * is implemented in child class AdminExtractor.
 */
abstract class AbstractAdminExtractor {

    protected abstract Admin extract(ResultSet rs) throws SQLException;

    public Set<Admin> extractAll(ResultSet rs) throws SQLException {
        Set<Admin> result = new TreeSet<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
