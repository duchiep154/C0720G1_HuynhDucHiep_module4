package com.codegym.common;
import java.io.Serializable;
import java.util.stream.Stream;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator implements IdentifierGenerator {
    private static final String PREFIX = "MGD-";
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = "SELECT c.id FROM GiaoDich c";
        Stream<String> ids = session.createQuery(query, String.class).stream();
        Long max = ids.map(o -> o.replace(PREFIX , "")).mapToLong(Long::parseLong).max().orElse(0L);
        return PREFIX  + (String.format("%04d", max + 1));
    }
}