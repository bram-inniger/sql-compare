package be.inniger.scratch.sql;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.dao.DdlDao;
import be.inniger.scratch.sql.dao.PersonDao;
import be.inniger.scratch.sql.dao.jdbctemplate.JdbcTemplateAddressDao;
import be.inniger.scratch.sql.dao.jdbctemplate.JdbcTemplateDdlDao;
import be.inniger.scratch.sql.dao.jdbctemplate.JdbcTemplatePersonDao;
import be.inniger.scratch.sql.dao.jdbi.JdbiAddressDao;
import be.inniger.scratch.sql.dao.jdbi.JdbiDdlDao;
import be.inniger.scratch.sql.dao.jdbi.JdbiPersonDao;
import be.inniger.scratch.sql.dao.jooq.JooqAddressDao;
import be.inniger.scratch.sql.dao.jooq.JooqDdlDao;
import be.inniger.scratch.sql.dao.jooq.JooqPersonDao;
import be.inniger.scratch.sql.dao.mybatis.MyBatisAddressMapper;
import be.inniger.scratch.sql.dao.mybatis.MyBatisDdlMapper;
import be.inniger.scratch.sql.dao.mybatis.MyBatisPersonMapper;
import be.inniger.scratch.sql.model.Address;
import be.inniger.scratch.sql.model.Person;
import be.inniger.scratch.sql.service.AddressService;
import be.inniger.scratch.sql.service.DdlService;
import be.inniger.scratch.sql.service.PersonService;
import be.inniger.scratch.sql.util.FunctionUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jdbi.v3.core.Jdbi;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class App<DB> {

    private final Runnable closer;
    @Getter
    private final AddressService addressService;
    @Getter
    private final PersonService personService;

    private App(
            Function<DataSource, DB> dbFunc,
            Function<DB, DdlDao> ddlDaoFunc,
            Function<DB, AddressDao> addressDaoFunc,
            Function<DB, PersonDao> personDaoFunc,
            Consumer<DB> closeFunc) {
        var db = dbFunc.apply(sqliteDataSource());

        var ddlDao = ddlDaoFunc.apply(db);
        var addressDao = addressDaoFunc.apply(db);
        var personDao = personDaoFunc.apply(db);

        new DdlService(ddlDao)
                .resetDB();

        this.closer = () -> closeFunc.accept(db);
        this.addressService = new AddressService(addressDao);
        this.personService = new PersonService(addressDao, personDao);
    }

    private DataSource sqliteDataSource() {
        var dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:person.sqlite");
        return dataSource;
    }

    public static App<?> createAndStart(SqlBackend backend) {
        return backend.appSupplier.get();
    }

    public void stop() {
        closer.run();
    }

    @RequiredArgsConstructor
    public enum SqlBackend {
        MY_BATIS(() -> new App<>(
                dataSource -> {
                    var environment = new Environment("Development", new JdbcTransactionFactory(), dataSource);

                    var configuration = new Configuration(environment);
                    configuration.addMappers("be.inniger.scratch.sql.dao.mybatis");
                    configuration.setMapUnderscoreToCamelCase(true);

                    return new SqlSessionFactoryBuilder()
                            .build(configuration)
                            .openSession(true);
                },
                sqlSession -> sqlSession.getMapper(MyBatisDdlMapper.class),
                sqlSession -> sqlSession.getMapper(MyBatisAddressMapper.class),
                sqlSession -> sqlSession.getMapper(MyBatisPersonMapper.class),
                SqlSession::close) {
        }),
        JOOQ(() -> new App<>(
                dataSource -> {
                    System.getProperties().setProperty("org.jooq.no-logo", "true");
                    return DSL.using(dataSource, SQLDialect.SQLITE);
                },
                JooqDdlDao::new,
                JooqAddressDao::new,
                JooqPersonDao::new,
                DSLContext::close) {
        }),
        JDBI(() -> new App<>(
                dataSource -> Jdbi.create(dataSource)
                        .registerRowMapper(Address.class, (rs, __) -> new Address(
                                rs.getInt("id"),
                                rs.getString("street"),
                                rs.getInt("person_id")))
                        .registerRowMapper(Person.class, (rs, __) -> new Person(
                                rs.getInt("id"),
                                rs.getString("name"))),
                JdbiDdlDao::new,
                JdbiAddressDao::new,
                JdbiPersonDao::new,
                FunctionUtil::noop) {
        }),
        JDBC_TEMPLATE(() -> new App<>(
                NamedParameterJdbcTemplate::new,
                JdbcTemplateDdlDao::new,
                JdbcTemplateAddressDao::new,
                JdbcTemplatePersonDao::new,
                FunctionUtil::noop) {
        });

        private final Supplier<App<?>> appSupplier;
    }
}
