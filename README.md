# JavaNoSQLv2

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
export OCI_use_obo_token=true
export OCI_AUTH=InstancePrincipal
export OCI_obo_token=$(cat /etc/oci/delegation_token) 

git clone https://github.com/dario-vega/JavaNoSQLv2.git
cd ~/JavaNoSQLv2
mvn compile
mvn exec:java -Dexec.mainClass="HelloWorld"

````
