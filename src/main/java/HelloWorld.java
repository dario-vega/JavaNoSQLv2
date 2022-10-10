import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.NoSQLHandleConfig;
import oracle.nosql.driver.NoSQLHandleFactory;
import oracle.nosql.driver.kv.StoreAccessTokenProvider ;


import oracle.nosql.driver.Region;
import oracle.nosql.driver.iam.SignatureProvider;
import oracle.nosql.driver.ops.*;
import oracle.nosql.driver.values.JsonOptions;
import oracle.nosql.driver.values.MapValue;

import java.io.File;
import java.util.List;

public class HelloWorld {
    private static NoSQLHandle getNoSQLConnection() {

        char[] pwd = {'D', 'r', 'i', 'v', 'e', 'r', 'P', 'a', 's', 's' , '@', '@', '1', '2', '3'};

        NoSQLHandleConfig config = new NoSQLHandleConfig("https://node1-nosql:8443");

        config.setAuthorizationProvider(new StoreAccessTokenProvider("driver_user", pwd));

        return( NoSQLHandleFactory.createNoSQLHandle(config) );
    }


    private static void readDemo(NoSQLHandle serviceHandle) {
        String stmt = "SELECT * FROM blogtable LIMIT 10";
        QueryRequest queryRequest = new QueryRequest().setStatement(stmt);
        System.out.println(stmt);
        try (QueryIterableResult results = serviceHandle.queryIterable(queryRequest)) {
                for (MapValue qval : results) {
                    System.out.println( qval.toString());
                }
         }
    }

    public static void main (String args[]) {
        try {
            NoSQLHandle handle = getNoSQLConnection();
            readDemo(handle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            //handle.close();
        }
    }
}
