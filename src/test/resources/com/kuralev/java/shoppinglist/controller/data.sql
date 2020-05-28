-- List 1
INSERT INTO lists (uuid) VALUES ('952911af-0bbb-45a9-b946-3f4f0a6bff20');

-- Items for the List 1
INSERT INTO items (internal_id, checked, deleted, description, id, priority, list_id)
VALUES (1, false, false, 'list 1 item 1', 1, 0, '952911af-0bbb-45a9-b946-3f4f0a6bff20');

INSERT INTO items (internal_id, checked, deleted, description, id, priority, list_id)
VALUES (2, true, false, 'list 1 item 2', 2, 10, '952911af-0bbb-45a9-b946-3f4f0a6bff20');

INSERT INTO items (internal_id, checked, deleted, description, id, priority, list_id)
VALUES (3, false, true, 'list 1 item 3', 3, 20, '952911af-0bbb-45a9-b946-3f4f0a6bff20');

-- List 2
INSERT INTO lists (uuid) VALUES ('fa9eb87a-a0dd-11ea-b6f2-e34907e6fd68');

-- Items for the List 2
INSERT INTO items (internal_id, checked, deleted, description, id, priority, list_id)
VALUES (4, false, false, 'list 2 item 1', 1, 0, 'fa9eb87a-a0dd-11ea-b6f2-e34907e6fd68');

INSERT INTO items (internal_id, checked, deleted, description, id, priority, list_id)
VALUES (5, true, false, 'list 2 item 2', 2, 10, 'fa9eb87a-a0dd-11ea-b6f2-e34907e6fd68');

