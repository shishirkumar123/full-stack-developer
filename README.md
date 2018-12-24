# full-stack-developer
Demonstration of Sprinboot Application with code coverage

I have implemented a simple rest API which can be used to get list of an entity called 'Privileges'.  The application runs fine. Once you run the main method, you can go to the browser(or postman) and hit the URL 'http://<machine-name>:8080/api/v1/privileges', you will see the result as shown below -

[
    {
        "privilegeId": 1,
        "privilegeName": "Add User",
        "privilegeDescription": "Adds a new User"
    },
    {
        "privilegeId": 2,
        "privilegeName": "Delete User",
        "privilegeDescription": "Deletes a User"
    },
    {
        "privilegeId": 3,
        "privilegeName": "Modify User",
        "privilegeDescription": "Modifies a User"
    },
    {
        "privilegeId": 4,
        "privilegeName": "View Users",
        "privilegeDescription": "Shows the list of all users"
    }
]

I have done the code coverage for one class only(FullStackApplicationTests) to demonstrate how it needs to be done. 
