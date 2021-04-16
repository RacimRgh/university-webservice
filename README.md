# README

## _Projet Universities Web services_

Made by the Righi-Ratovo Group (Righi Racim & Ratovo Maeva)
April 2021, at CY Paris Université en Licence Informatique 3

This project's authors can be reached through these mail adresses : rtv.maeva@gmail.com, racim458@gmail.com

In this README, we'ell see each of these points :

- The provided services
- The client
- Demonstration

## Services

The project is provided both as a SOAP and REST webservice.
Both offer the same services, here is a short description of what they are:

- Adding a university: Using a name, we generate a unique ID and add it to the Hashmap. The service then calls the Open Street Map Api **Nominatim** to get the full address, longitude and latitude of the university.
- Adding a speciality: Using a field name, year and path name, we generate a speciality and add it to the hashmap, we also map it to a university using another Hashmap that has the speciality id as key, and university id as value. This way, we can have multiple specialities in a university, but a speciality only concerns one university.
- Getting the added data: after filling the hashmaps, we can retrieve the data using the respective methods to each service. We can get a university, a speciality, a speciality in a specific university, all the universities, all the specialities, and finally all the specialities in a specific university.
- We can also delete the added data using the id's.

### WSDL

- Service endpoint: http://localhost:8080/UniversityManagement/services
- Operations: addUniversity, getUniversity, removeUniversity, addSpeciality, getSpeciality, removeSpeciality, addSpecialityToUniversity, getSpecialityFromUniversity, getAllUniversities, getAllSpecialities, getSpecialitiesFromUni.

### REST

- Web service URL: http://localhost:8080/UniversityManagement/api
- University path: http://localhost:8080/UniversityManagement/api/university/{id}
- Speciality path in a specific university: http://localhost:8080/UniversityManagement/api/university/{id_u}/speciality{id_s}
- All specialities path: http://localhost:8080/UniversityManagement/api/speciality

## Client

The client show a console menu to the user and allows him to do one of the following operations:
![menu](Screenshots/menu.png)

- Adds a university.
  ![add_uni](Screenshots/add_uni.png)
- Maps a speciality to a university.
  ![add_spe](Screenshots/add_spe.png)
- Gets a university.
  ![get_uni](Screenshots/get_uni.png)
- Gets all universities.
  ![get_all_uni](Screenshots/get_all_uni.png)
- Get all specialities.
  ![get_all_spe](Screenshots/get_all_spe.png)
- Get address from a name.
  ![get_address](Screenshots/get_address.png)
- Stops.

## Demonstration

To show you the functionnality of our implementation we've chosen to show you how we can add universities thanks Postman with these screenshots :

- Get address from a name.
  ![get_address_postman](Screenshots/get_address_postman.png)
- Add university SOAP
  ![add_uni_soap_postman](Screenshots/add_uni_soap.png)
- Add university REST
  ![add_uni_rest_postman](Screenshots/add_uni_postman_soap.png)
- Get university REST
  ![get_uni_rest_postman](Screenshots/get_uni_postman.png)
