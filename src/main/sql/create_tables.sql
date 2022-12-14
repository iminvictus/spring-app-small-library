create table person (
                        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        name varchar(100) NOT NULL UNIQUE,
                        birth int CHECK (birth > 1922)
);

create table book (
                      id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      title varchar(200) NOT NULL,
                      author varchar(100) NOT NULL,
                      year int NOT NULL,
                      person_id int REFERENCES person(id) ON DELETE SET NULL
)