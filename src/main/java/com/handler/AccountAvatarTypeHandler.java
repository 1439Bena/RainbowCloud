package com.handler;

import com.utils.impl.ImageUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Cc
 * 2024-05-24
 */
public class AccountAvatarTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, s);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        byte[] avatar = resultSet.getBytes(s);
        return avatar != null ? new ImageUtils().byteToString(avatar) : null;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        byte[] avatar = resultSet.getBytes(i);
        return avatar != null ? new ImageUtils().byteToString(avatar) : null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        byte[] avatar = callableStatement.getBytes(i);
        return avatar != null ? new ImageUtils().byteToString(avatar) : null;
    }
}
