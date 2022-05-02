import os
import oci

import borneo
import sys
from borneo import (
    AuthorizationProvider, DeleteRequest, GetRequest,
    IllegalArgumentException, NoSQLHandle, NoSQLHandleConfig, PutRequest,
    QueryRequest, Regions, TableLimits, TableRequest)
from borneo.iam import SignatureProvider

signer = oci.auth.signers.InstancePrincipalsDelegationTokenSigner (delegation_token=os.getenv('OCI_obo_token'))
#NoSQLprovider = SignatureProvider(provider=signer)
NoSQLprovider = SignatureProvider(provider=signer, region=os.getenv('OCI_REGION'))
NoSQLconfig = NoSQLHandleConfig(os.getenv('OCI_REGION'),NoSQLprovider).set_logger(None).set_default_compartment(os.getenv('NOSQL_COMP_ID'))
#NoSQLconfig = NoSQLHandleConfig('us-ashburn-1',NoSQLprovider).set_logger(None).set_default_compartment(os.getenv('NOSQL_COMP_ID'))
handle = NoSQLHandle(NoSQLconfig)
#
# Get the row
#
request = GetRequest().set_key({'ticketNo': '176233524485'}).set_table_name('demo')
result = handle.get(request)
print(' get: ' + str(result))
handle.close();
