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
 * 2024-05-23
 */
public class PostPimageTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setBytes(i, new ImageUtils().stringToByte(s));
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        byte[] pImage = resultSet.getBytes(s);
        return pImage != null ? new ImageUtils().byteToString(pImage) : null;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        byte[] pImage = resultSet.getBytes(i);
        return pImage != null ? new ImageUtils().byteToString(pImage) : null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        byte[] pImage = callableStatement.getBytes(i);
        return pImage != null ? new ImageUtils().byteToString(pImage) : null;
    }
}
