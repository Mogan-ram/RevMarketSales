package Util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static final String url;
    private static final String username;
    private static final String password;
    private static final String driver;

    static {
        try{
            logger.info("Loading database configuration...");
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("config.properties");
            if(input == null){
                throw new RuntimeException("config.properties not found in the res folder.");
            }
            props.load(input);
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

//            Class.forName(driver);
            logger.info("Database driver loaded: {}",driver);

        }catch (Exception e){
            logger.error("Error loading DB config",e);
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() throws SQLException{
        logger.info("Establishing database connection...");
            logger.info("Database connection established successfully");
        return DriverManager.getConnection(url,username,password);
    }
}
