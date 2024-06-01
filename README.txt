API Endpoints

Create Treatment
URL: /api/treatments
Method: POST
Request Body:
json
Copy code
{
  "advice": "Take medicine with water",
  "medicine": {
    "id": 1
  },
  "patient": {
    "id": 1
  }
}
Response: Status 201 Created with created treatment details.

Get Treatment by ID
URL: /api/treatments/{id}
Method: GET
Response: Status 200 OK with treatment details.