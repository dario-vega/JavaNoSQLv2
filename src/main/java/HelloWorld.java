import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.NoSQLHandleConfig;
import oracle.nosql.driver.NoSQLHandleFactory;
import oracle.nosql.driver.Region;
import oracle.nosql.driver.iam.SignatureProvider;
import oracle.nosql.driver.ops.*;
import oracle.nosql.driver.values.JsonOptions;
import oracle.nosql.driver.values.MapValue;

import java.io.File;
import java.util.List;

public class HelloWorld {
    private static NoSQLHandle getNoSQLConnection() {

        SignatureProvider authProvider =
                SignatureProvider.createWithInstancePrincipalForDelegation(System.getenv("OCI_obo_token"));
        NoSQLHandleConfig config = new NoSQLHandleConfig(System.getenv("OCI_REGION"), authProvider);
        config.setDefaultCompartment(System.getenv("NOSQL_COMP_ID")) ;
        System.out.println("Application Running");
        System.out.println(System.getenv("OCI_REGION"));
        System.out.println(System.getenv("NOSQL_COMP_ID"));
        return( NoSQLHandleFactory.createNoSQLHandle(config) );
    }


    private static void readDemo(NoSQLHandle serviceHandle) {
        QueryRequest queryRequest = new QueryRequest().setStatement("SELECT * FROM demo");
        try {
            do {
                QueryResult queryResult = serviceHandle.query(queryRequest);
                /* process current set of results */
                List<MapValue> results = queryResult.getResults();
                for (MapValue qval : results) {
                    System.out.println( qval.toString());
                }
            } while (!queryRequest.isDone());
         } finally {
            queryRequest.close();
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
            handle.close();
        }
    }
}
