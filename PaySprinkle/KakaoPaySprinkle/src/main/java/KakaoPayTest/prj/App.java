package KakaoPayTest.prj;


import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hello world!
 *
 */
//! SpringBoost를 사용하기 위함
@SpringBootApplication
public class App implements ApplicationRunner
{
	final static Logger logger = LoggerFactory.getLogger(App.class);
	
	@Autowired
	public static RoomMng	m_RoomMng;
	
    public static void main( String[] args )
    {	
        SpringApplication app = new SpringApplication(App.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);

    }
    

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Start REST API server!!!");
        m_RoomMng = new RoomMng();
        
        Iterator<String> iter = args.getOptionNames().iterator();
        while( iter.hasNext() ) {
            String key = iter.next();
            Object value = args.getOptionValues(key);
            logger.info("{}={}", key, value );
        }
    }

}
