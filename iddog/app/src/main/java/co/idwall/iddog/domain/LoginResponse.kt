package co.idwall.iddog.domain


// example of the JSON returned from the API
/*
 {
   "user": {
     "_id": "5f31b8d69113f96bb734977d",
     "email": "fellipecaetano42@gmail.com",
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWYzMWI4ZDY5MTEzZjk2YmI3MzQ5NzdkIiwiaWF0IjoxNTk3MDk0MTAyLCJleHAiOjE1OTgzOTAxMDJ9.ycSYMoG-qU38L49qhwdm-oD3z4k7lsQAa0dxTZn_GQQ",
     "createdAt": "2020-08-10T21:15:02.600Z",
     "updatedAt": "2020-08-10T21:15:02.600Z",
     "__v": 0
   }
 }
 */


data class LoginResponse(val user: User)

data class User(val token: String)