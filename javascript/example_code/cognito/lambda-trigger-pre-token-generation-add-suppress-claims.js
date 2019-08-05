// Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// 
// This file is licensed under the Apache License, Version 2.0 (the "License").
// You may not use this file except in compliance with the License. A copy of
// the License is located at
// 
// http://aws.amazon.com/apache2.0/
// 
// This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// CONDITIONS OF ANY KIND, either express or implied. See the License for the
// specific language governing permissions and limitations under the License.

// snippet-sourcedescription:[This example uses the Pre Token Generation Lambda to add a new claim and suppresses an existing one.]
// snippet-service:[cognito-idp]
// snippet-keyword:[JavaScript]
// snippet-keyword:[Amazon Cognito]
// snippet-keyword:[Code Sample]
// snippet-keyword:[lambda_trigger]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[2019-01-30]
// snippet-sourceauthor:[AWS]

// snippet-start:[cognito.javascript.lambda-trigger.pre-token-generation-add-suppress-claims]
exports.handler = (event, context, callback) => {
    event.response = {
        "claimsOverrideDetails": {
            "claimsToAddOrOverride": {
                "attribute_key2": "attribute_value2",
                "attribute_key": "attribute_value"
            },
            "claimsToSuppress": ["email"]
        }
    };

    // Return to Amazon Cognito
    callback(null, event);
};
// snippet-end:[cognito.javascript.lambda-trigger.pre-token-generation-add-suppress-claims]