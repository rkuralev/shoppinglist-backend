DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS lists;

CREATE TABLE lists
(
    uuid varchar(255) NOT NULL PRIMARY KEY
);

CREATE TABLE items
(
    internal_id bigint NOT NULL,
    checked bit,
    deleted bit,
    description varchar(255),
    id int,
    priority int,
    list_id varchar(255) NOT NULL,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (list_id) REFERENCES lists(uuid)
);