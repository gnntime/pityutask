# pityutask
My solution for the extra Spring project task given by Pityu

## Create a PetShelter

### Basic functionalities

- you should have a Human class with id, name, age fields
- human should be stored in a database
- in the Controller there should methods that renders an HTML GET /list-humans where all the fields are displayed of the humans stored in the database
- create an POST /add-human endpoint where you can add new human
extend the GET /list-humans with two extra buttons, one which can delete (GET /delete/{id}) the given Human and one which can edit (GET /edit/id) the given Human
- (For editing and creating a new human, you can use only 1, or 2 HTML pages. One is preferred)
- create a GET /api/human/{id} endpoint which returns a json with the values of the Human class - age should not be displayed- if the human exists on the given index and with a status code 200 and if not, then with an error message {"error": "No human on the given index {id}"}
-Create a DELETE /api/human/{id} endpoint which can delete a human on the given index and return status code 200, if there is no human present on the given index, should return with 404 status code and with the same error message as at GET/api/human/{id}

### Extended functionality
- Validation POST /add-human if some of the fields are missing or if a human exists with the given name then redirect to the /add-human endpoint and the HTML should display an error message ("Something went wrong, please check if the fields are filled out or use another name, because the name is already taken") and prefill the input fields with the already provided Human fields
- Create an Pet class with id, name
- Connect the Pet with the Human (one Human can have more Pets but on Pet can have only one Human)
- create a  GET/list-pets endpoint where you display all the fields of your pet, including Human's name (if there is no Human provided, then display "No owner yet")
- create POST /add-pet endpoint, where you can save the pets, do some errorHandling here as well, same es with the /add-human endpoint. This endpoint should have a dropdown menu, where all the Humans' names are displayed and it should be saved at the pet
- add an edit/delete button here as well
-GET /api/pet-names-older-human endpoint which returns with all the petnames, where the owner is older than an integer provided as request parameter +status code 200 and if there is no such pet, then with an error {"error": "None of the pets' owners are older then {age}"} + status code 404
- create an PUT /api/human/{id}, where in the requestBody you provide the new name, age for that given human and if the human exists on the given index, return with status code 200 and with the name, age and petname of the human (if there is a pet provided)


