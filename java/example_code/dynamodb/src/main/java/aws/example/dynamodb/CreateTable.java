/*
   Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.

   This file is licensed under the Apache License, Version 2.0 (the "License").
   You may not use this file except in compliance with the License. A copy of
   the License is located at

    http://aws.amazon.com/apache2.0/

   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied. See the License for the
   specific language governing permissions and limitations under the License.
*/
package aws.example.dynamodb;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.AmazonServiceException;

/**
 * Create a DynamoDB table.
 *
 * Takes the name of the table to create. The table will contain a single
 * primary key, "Name".
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class CreateTable
{
    public static void main(String[] args)
    {
        final String USAGE = "\n" +
            "Usage:\n" +
            "    CreateTable <table>\n\n" +
            "Where:\n" +
            "    table - the table to create.\n\n" +
            "Example:\n" +
            "    CreateTable HelloTable\n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        /* Read the name from command args */
        String table_name = args[0];

        System.out.format("Creating table %s\n with a simple primary key: \"Name\".");

        CreateTableRequest request = new CreateTableRequest()
            .withAttributeDefinitions(new AttributeDefinition(
                     "Name", ScalarAttributeType.S))
            .withKeySchema(new KeySchemaElement("Name", KeyType.HASH))
            .withProvisionedThroughput(new ProvisionedThroughput(
                     new Long(10), new Long(10)))
            .withTableName(table_name);

        final AmazonDynamoDBClient ddb = new AmazonDynamoDBClient();

        try {
            CreateTableResult result = ddb.createTable(request);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }
}

