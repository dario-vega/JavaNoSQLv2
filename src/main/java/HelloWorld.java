import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.NoSQLHandleConfig;
import oracle.nosql.driver.NoSQLHandleFactory;
import oracle.nosql.driver.Region;
import oracle.nosql.driver.iam.SignatureProvider;
import oracle.nosql.driver.ops.*;
import oracle.nosql.driver.values.JsonOptions;
import oracle.nosql.driver.values.MapValue;

import java.io.File;

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


    private static void readOneRecord(NoSQLHandle serviceHandle) {
        GetRequest getRequest = new GetRequest();
        getRequest.setKey(new MapValue().put("ticketNo", "176233524485"));
        getRequest.setTableName("demo");
        GetResult gr = serviceHandle.get(getRequest);
        if (gr != null) {
            System.out.println(gr.getValue().toJson(new JsonOptions()));
        }
    }

    public static void main (String args[]) {
        try {
            NoSQLHandle handle = getNoSQLConnection();
            readOneRecord(handle);
            handle.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
