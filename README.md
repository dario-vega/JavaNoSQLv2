# JavaNoSQLv2

# on-premise Secure Cluster

Execute a Java Program using an external certificate - Javax.net.ssl.trustStore and javax.net.ssl.trustStorePassword

````
git clone https://github.com/dario-vega/JavaNoSQLv2.git
cd ~/JavaNoSQLv2
mvn compile
mvn exec:java -Djavax.net.ssl.trustStore=/home/opc/proxy/driver.trust -Djavax.net.ssl.trustStorePassword=123456 -Dexec.mainClass="HelloWorld"
````

# using delegationToken from Cloud Shell

Execute a Java Program using delegationToken from Cloud Shell

ENV Variables USED
- OCI_REGION
- OCI_obo_token
ENV Variables SET
- NOSQL_COMP_ID


````
CMP_ID=`oci iam compartment list --name  demonosql | jq -r '."data"[].id'`
export NOSQL_COMP_ID=$CMP_ID
echo $OCI_REGION
echo $NOSQL_COMP_ID
export OCI_obo_token=$(cat $OCI_obo_token_path) 

git clone https://github.com/dario-vega/JavaNoSQLv2.git
cd ~/JavaNoSQLv2
mvn compile
mvn exec:java -Dexec.mainClass="HelloWorld"

````
