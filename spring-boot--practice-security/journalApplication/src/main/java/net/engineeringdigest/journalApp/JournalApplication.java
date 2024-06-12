package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //used to Find @Transactional
public class JournalApplication {

    public static void main(String[] args) {
        System.out.println("Apka Program Chalra ha");SpringApplication.run(JournalApplication.class, args);
    }
     //Interface name is PlatformTransactionManager which is implemented by
         // MongoTransactionManager  . MongoDatabaseFactory is interface used to handle db transactions
    @Bean
    public PlatformTransactionManager kuchbhi(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
 }


}