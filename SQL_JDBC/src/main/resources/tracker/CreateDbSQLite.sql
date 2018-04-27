-- Create table Item
CREATE TABLE IF NOT EXISTS item(id INTEGER PRIMARY KEY, date DATASTAMP not null, name VARCHAR(200) not null, description TEXT);

-- Create table Comment
CREATE TABLE IF NOT EXISTS comment(id INTEGER PRIMARY KEY, text TEXT, item_id INTEGER REFERENCES item(id));