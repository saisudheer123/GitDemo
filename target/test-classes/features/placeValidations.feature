Feature: validating Place API's

@AddPlace
Scenario Outline: to verify whether place is adding succesfully using AddPlaceAPI
Given pass the AddPlace api payload "<name>" "<language>" "<address>"
When  user hit the "AddPlaceAPI" api server with "post" httprequest
Then  API call got success with expected response code 200
And   "status" in response body as "OK"
And   "scope" in response body as "APP"
And   verify newly added "place_id" in AddPlaceAPI is able to get using "GetPlaceAPI" api server with "Get" httprequest and verify "<name>" is matched
Examples: 
        |name|language|address|
        |sai|english|ongole|
#       |sudheer|telugu|chirala|

@DeletePlace
Scenario: to verify whether place is deleted successfully using DeletePlaceAPI
Given pass the DeletePlace api payload
When user hit the "DeletePlaceAPI" api server with "delete" httprequest
Then API call got success with expected response code 200
And   "status" in response body as "OK"