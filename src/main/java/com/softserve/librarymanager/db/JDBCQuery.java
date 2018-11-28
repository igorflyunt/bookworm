package com.softserve.librarymanager.db;

import com.softserve.librarymanager.dao.mapper.EntityMapper;
import com.softserve.librarymanager.model.AbstractEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class JDBCQuery {

    private JDBCQuery() {}

    public static <T extends AbstractEntity> T selectOne(String query, EntityMapper<T> entityMapper,
                                                          Object... paramArgs) {
        T entity = null;
        try {
            PreparedStatement st = setupPreparedStatement(query, paramArgs);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                entity = entityMapper.mapToEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }


    public static boolean execute(String query, Object... paramArgs) {
        try {
            PreparedStatement st = setupPreparedStatement(query, paramArgs);
            return st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends AbstractEntity> List<T> selectMany(String query, EntityMapper<T> entityMapper, Object... paramArgs) {
        List<T> entities = new ArrayList<>();
        try {
            PreparedStatement st = setupPreparedStatement(query, paramArgs);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                entities.add(entityMapper.mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public static <E extends AbstractEntity> void update(E entity, String query, Object... paramArgs) {
        try {
            PreparedStatement st = setupPreparedStatement(query, paramArgs);
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next() && entity != null)
                entity.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query);
    }

    private static PreparedStatement setupPreparedStatement(String query, Object[] paramArgs) throws SQLException {
        PreparedStatement st = DataSource.getDbConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < paramArgs.length; i++) {
            Object paramArg = paramArgs[i];
            st.setObject(i + 1, paramArg);
        }
        return st;
    }
}