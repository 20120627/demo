CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    due_date DATE,
    priority INT,
    completed BOOLEAN DEFAULT FALSE,
    dependent_task_id INT DEFAULT NULL
);
INSERT INTO task (title, description, due_date, priority, completed, dependent_task_id) VALUES
('Task 1', 'Description for Task 1', '2023-12-01', 1, FALSE, NULL),
('Task 2', 'Description for Task 2', '2023-12-02', 2, FALSE, NULL),
('Task 3', 'Description for Task 3', '2023-12-03', 3, FALSE, NULL),
('Task 4', 'Description for Task 4', '2023-12-04', 4, FALSE, NULL),
('Task 5', 'Description for Task 5', '2023-12-05', 5, FALSE, NULL),
('Task 6', 'Description for Task 6', '2023-12-06', 1, FALSE, NULL),
('Task 7', 'Description for Task 7', '2023-12-07', 2, FALSE, NULL),
('Task 8', 'Description for Task 8', '2023-12-08', 3, FALSE, NULL),
('Task 9', 'Description for Task 9', '2023-12-09', 4, FALSE, NULL),
('Task 10', 'Description for Task 10', '2023-12-10', 5, FALSE, NULL);