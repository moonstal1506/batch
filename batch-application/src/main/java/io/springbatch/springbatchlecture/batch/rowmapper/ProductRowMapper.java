package io.springbatch.springbatchlecture.batch.rowmapper;

import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductVO> {
    @Override
    public ProductVO mapRow(ResultSet resultSet, int i) throws SQLException {
        return ProductVO.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getInt("price"))
                .type(resultSet.getString("type"))
                .build();
    }
}
