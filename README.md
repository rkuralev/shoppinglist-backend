# Shopping List Backend
Simple REST Backend for a Shopping List App
# Why I created this app
Though there are hundreds of thousands "shopping lists" in Google Play Market, I didn't find any which is simple, not ugly, supports multiple lists, 
list sharing and (most important) is privacy oriented which means that it doesn't include tracking components and doesn not require any information or registration for list sharing.

So I decided to develop my own shopping list. As I was learning Java development and was keen to try Spring Framework, I decided to start with a backend.

**Disclaimer:** I know that the most convenient way to share data on Android platform is Google Firebase, but I intentionnaly declined this option.
# How to use it
## Pre-reqs
* MySQL
* JRE11
## Sharing a list
**Request:**
```java
POST /lists

{
    "items": [
    {
            "description": "Bread",
            "id": 1,
            "checked": false,
            "priority": 0,
            "deleted": false
        },
    {
            "description": "Milk",
            "id": 2,
            "checked": true,
            "priority": 20,
            "deleted": false
        },
        {
            "description": "Butter",
            "id": 3,
            "checked": true,
            "priority": 10,
            "deleted": true
        }
    ]
}
```
**Response:**
```"952911af-0bbb-45a9-b946-3f4f0a6bff20"```

## Getting a shared list
**Request:**
```java
GET /lists/952911af-0bbb-45a9-b946-3f4f0a6bff20
```
**Response:**
```java
{
    "items": [
    {
            "description": "Bread",
            "id": 1,
            "checked": false,
            "priority": 0,
            "deleted": false
        },
    {
            "description": "Milk",
            "id": 2,
            "checked": true,
            "priority": 20,
            "deleted": false
        },
        {
            "description": "Butter",
            "id": 3,
            "checked": true,
            "priority": 10,
            "deleted": true
        }
    ]
}
```
## Watching for a list updates
An app should poll data by sending every N secods the following request:
```java
GET /lists/952911af-0bbb-45a9-b946-3f4f0a6bff20/items
```
Response will be:
```java
{
        {
            "description": "Bread",
            "id": 1,
            "checked": false,
            "priority": 0,
            "deleted": false
        },
        {
            "description": "Milk",
            "id": 2,
            "checked": true,
            "priority": 20,
            "deleted": false
        },
        {
            "description": "Butter",
            "id": 3,
            "checked": true,
            "priority": 10,
            "deleted": true
         }
 }
```

An app has to compare its local version of the list with the one from server and if there any differences - apply them locally.
## Sending update to a list
Whenever a list is updated (item added, deleted or modified), an app has to notify server about a change.
### Adding new item
```java
POST /lists/952911af-0bbb-45a9-b946-3f4f0a6bff20/items
{
    "description": "Facial mask",
    "id": 4,
    "checked": false,
    "priority": 50,
    "deleted": false
}
```
Response will be `HTTP/201 Created`

### Fetching a specific item
**Request:**
```java
GET /lists/952911af-0bbb-45a9-b946-3f4f0a6bff20/items/4
```
**Response:**
```java
{
    "description": "Facial mask",
    "id": 4,
    "checked": false,
    "priority": 50,
    "deleted": false
}
```
### Updating an existing item
**Request:**
```java
POST /lists/952911af-0bbb-45a9-b946-3f4f0a6bff20/items/4
{
    "description": "Facial mask",
    "id": 4,
    "checked": true,
    "priority": 50,
    "deleted": false
}
```
Response will be `HTTP/304 Modified`
### Deleting an existing item
**Note:** items are not deleted only marked as deleted, otherwise it will be difficult to understand on a client if an item was deleted or never existed
**Request:**
```java
DELETE /lists/952911af-0bbb-45a9-b946-3f4f0a6bff20/items/4
```
Response will be `HTTP/200 OK`
# Next steps
* Add tests
* Improve errors handling
* Implementing a job to clean deleted items from a database
