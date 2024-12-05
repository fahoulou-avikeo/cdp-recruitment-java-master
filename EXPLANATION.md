# Introduction

I would like first to thank you for the time taken to read this paper.
I will share my thought through the following lines

## Issue analysis and resolution
1. Adding a review is not implemented yet
2. Based on the description provided for the issue, i can state that all write operations are not committed.
Reading the code, i found that there is the repository class 'EventRepository' annotated with @Transactional marker and a readonly attribute.
This prevent operation from being committed

Simplify solution should be to remove the readonly attribute from the annotation; but
taking coding standard and best practices into account, i modify the application structure by providing
package for each layer (service, controller, entity ...) for clear separation of concerns
Then, I remove the @Transactional at the repository level and put it in the service layer
add a globally readonly scope and override this scope for modifying methods

