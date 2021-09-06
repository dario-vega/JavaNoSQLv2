public class HelloWorld {
    private static NoSQLHandle getNoSQLConnection() {

        SignatureProvider authProvider = new SignatureProvider(
                "Your tenant OCID goes here",
                "Your user OCID goes here",
                "Your key fingerprint goes here",
                new File("~/.oci/oci_api_key.pem"), // path to your private key file
                "The pass phrase for your key goes here".toCharArray());
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
