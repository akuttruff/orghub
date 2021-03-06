# orghub

A community organizing tool for managing contacts, sending alerts, publishing events, etc

## Setup

* install jdk (version 8 recommended)
* install [leiningen](https://leiningen.org/)
* start server and navigate to [localhost:3449](http://localhost:3449/).
  - `lein figwheel`

## TODOs
* finish hooking up authentication
* list views for groups, contacts, etc
* add db encryption for sensitive data
* wire up IDB in between server api calls and front-end
* add more items to this list :)

## License

Copyright © 2018 Christopher Kuttruff

The source code for this project (all code existing within the `src/` and `migrations/` subdirectories) is licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html) (see LICENSE.txt).  All other dependencies and linked libraries are under their respective licenses
