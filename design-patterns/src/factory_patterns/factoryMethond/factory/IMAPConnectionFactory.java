package factory_patterns.factoryMethond.factory;

import factory_patterns.factoryMethond.connection.Connection;
import factory_patterns.factoryMethond.connection.IMAPConnection;

/**
 * @author 无名氏
 * @date 2021/10/21
 * @Description: TODO
 */
public class IMAPConnectionFactory extends Factory{
    @Override
    public Connection produceConnection() {
        return new IMAPConnection();
    }
}
