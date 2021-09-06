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
                SignatureProvider.createWithInstancePrincipalForDelegation(System.getenv(OCI_obo_token));
        return(NoSQLHandleFactory.createNoSQLHandle(
                new NoSQLHandleConfig(Region.US_PHOENIX_1, authProvider)));
    }


    private static String readOneRecord(NoSQLHandle serviceHandle) {
        GetRequest getRequest = new GetRequest();
        getRequest.setKey(new MapValue().put("ticketNo", "1762386738153"));
        getRequest.setTableName("demo");

        long before = System.currentTimeMillis();
        GetResult gr = serviceHandle.get(getRequest);

        if (gr != null) {
            return (gr.getValue().toJson(new JsonOptions()));
        } else {
            return(null);
        }
    }

    public static void main (String args[]) {
        try {
            NoSQLHandle handle = getNoSQLConnection();
            System.out.println(readOneRecord(handle));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
