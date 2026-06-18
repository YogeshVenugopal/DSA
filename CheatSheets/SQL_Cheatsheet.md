# SQL Cheatsheet: Beginner to Advanced

A comprehensive guide to SQL queries, from basic operations to advanced techniques.

---

## Table of Contents

1. [Basics](#basics)
2. [SELECT & Filtering](#select--filtering)
3. [Joins](#joins)
4. [Aggregation & Grouping](#aggregation--grouping)
5. [Subqueries & CTEs](#subqueries--ctes)
6. [Advanced Queries](#advanced-queries)
7. [Database Management](#database-management)
8. [Performance Tips](#performance-tips)

---

## Basics

### Creating a Database

```sql
CREATE DATABASE database_name;
USE database_name;
```

### Creating a Table

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INT CHECK (age >= 18),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Data Types

```
INT             - Integer values
VARCHAR(n)      - Variable-length string (up to n characters)
CHAR(n)         - Fixed-length string
TEXT            - Long text
DATE            - YYYY-MM-DD
DATETIME        - YYYY-MM-DD HH:MM:SS
BOOLEAN         - TRUE/FALSE
DECIMAL(p,s)    - Decimal with precision
FLOAT           - Floating-point numbers
```

### Inserting Data

```sql
-- Single row
INSERT INTO users (name, email, age) 
VALUES ('John Doe', 'john@example.com', 25);

-- Multiple rows
INSERT INTO users (name, email, age) 
VALUES 
    ('Jane Doe', 'jane@example.com', 28),
    ('Bob Smith', 'bob@example.com', 35);
```

---

## SELECT & Filtering

### Basic SELECT

```sql
-- Select all columns
SELECT * FROM users;

-- Select specific columns
SELECT name, email FROM users;

-- Select with aliases
SELECT name AS 'Full Name', email AS 'Email Address' FROM users;
```

### WHERE Clause (Filtering)

```sql
-- Equals
SELECT * FROM users WHERE age = 25;

-- Not equals
SELECT * FROM users WHERE age != 25;
SELECT * FROM users WHERE age <> 25;

-- Greater than / Less than
SELECT * FROM users WHERE age > 25;
SELECT * FROM users WHERE age >= 25;

-- Between
SELECT * FROM users WHERE age BETWEEN 25 AND 35;

-- IN (multiple values)
SELECT * FROM users WHERE age IN (25, 30, 35);

-- LIKE (pattern matching)
SELECT * FROM users WHERE name LIKE 'John%';      -- Starts with John
SELECT * FROM users WHERE email LIKE '%@gmail.com'; -- Ends with @gmail.com
SELECT * FROM users WHERE name LIKE '%oh%';       -- Contains 'oh'

-- IS NULL / IS NOT NULL
SELECT * FROM users WHERE email IS NOT NULL;
SELECT * FROM users WHERE phone IS NULL;

-- Logical operators (AND, OR, NOT)
SELECT * FROM users WHERE age > 25 AND email LIKE '%@gmail.com';
SELECT * FROM users WHERE age < 20 OR age > 65;
SELECT * FROM users WHERE NOT (age BETWEEN 25 AND 35);
```

### ORDER BY (Sorting)

```sql
-- Ascending (default)
SELECT * FROM users ORDER BY age ASC;
SELECT * FROM users ORDER BY age;

-- Descending
SELECT * FROM users ORDER BY age DESC;

-- Multiple columns
SELECT * FROM users ORDER BY age DESC, name ASC;
```

### LIMIT & OFFSET

```sql
-- Get first 10 rows
SELECT * FROM users LIMIT 10;

-- Skip first 10 rows, get next 10
SELECT * FROM users LIMIT 10 OFFSET 10;

-- Alternative syntax
SELECT * FROM users LIMIT 10, 10;
```

### DISTINCT

```sql
-- Get unique values
SELECT DISTINCT age FROM users;
SELECT DISTINCT age, country FROM users;
```

---

## Joins

### INNER JOIN

```sql
SELECT u.name, o.order_id, o.amount
FROM users u
INNER JOIN orders o ON u.id = o.user_id;
```

### LEFT JOIN (LEFT OUTER JOIN)

```sql
-- Includes all rows from left table, matching rows from right table
SELECT u.name, o.order_id, o.amount
FROM users u
LEFT JOIN orders o ON u.id = o.user_id;
```

### RIGHT JOIN (RIGHT OUTER JOIN)

```sql
-- Includes all rows from right table, matching rows from left table
SELECT u.name, o.order_id, o.amount
FROM users u
RIGHT JOIN orders o ON u.id = o.user_id;
```

### FULL OUTER JOIN

```sql
-- Includes all rows from both tables
SELECT u.name, o.order_id, o.amount
FROM users u
FULL OUTER JOIN orders o ON u.id = o.user_id;
```

### CROSS JOIN

```sql
-- Cartesian product - every row from table1 with every row from table2
SELECT u.name, p.product_name
FROM users u
CROSS JOIN products p;
```

### Self Join

```sql
-- Join table with itself
SELECT e1.name AS employee, e2.name AS manager
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id;
```

### Multiple Joins

```sql
SELECT u.name, o.order_id, p.product_name
FROM users u
JOIN orders o ON u.id = o.user_id
JOIN products p ON o.product_id = p.id;
```

---

## Aggregation & Grouping

### Aggregate Functions

```sql
-- COUNT - number of rows
SELECT COUNT(*) FROM users;
SELECT COUNT(email) FROM users;  -- Counts non-NULL values

-- SUM - total of numeric column
SELECT SUM(amount) FROM orders;

-- AVG - average value
SELECT AVG(age) FROM users;

-- MIN - minimum value
SELECT MIN(age) FROM users;

-- MAX - maximum value
SELECT MAX(amount) FROM orders;

-- GROUP_CONCAT (MySQL) / STRING_AGG (PostgreSQL)
SELECT GROUP_CONCAT(name) FROM users;
```

### GROUP BY

```sql
-- Group by single column
SELECT age, COUNT(*) 
FROM users 
GROUP BY age;

-- Group by multiple columns
SELECT country, age, COUNT(*) 
FROM users 
GROUP BY country, age;

-- Group with SUM
SELECT user_id, SUM(amount) as total
FROM orders
GROUP BY user_id;
```

### HAVING (Filter grouped results)

```sql
-- Get ages with more than 5 users
SELECT age, COUNT(*) as count
FROM users
GROUP BY age
HAVING COUNT(*) > 5;

-- Get users who spent more than $1000
SELECT user_id, SUM(amount) as total
FROM orders
GROUP BY user_id
HAVING SUM(amount) > 1000;
```

### CASE (Conditional Logic)

```sql
SELECT 
    name,
    CASE 
        WHEN age < 18 THEN 'Minor'
        WHEN age >= 18 AND age < 65 THEN 'Adult'
        ELSE 'Senior'
    END as age_category
FROM users;

-- CASE with aggregation
SELECT 
    SUM(CASE WHEN status = 'completed' THEN amount ELSE 0 END) as completed_sales,
    SUM(CASE WHEN status = 'pending' THEN amount ELSE 0 END) as pending_sales
FROM orders;
```

---

## Subqueries & CTEs

### Subqueries in SELECT

```sql
SELECT 
    name,
    (SELECT COUNT(*) FROM orders WHERE user_id = users.id) as order_count
FROM users;
```

### Subqueries in WHERE

```sql
-- Get users who placed orders
SELECT * FROM users 
WHERE id IN (SELECT DISTINCT user_id FROM orders);

-- Get users with above-average age
SELECT * FROM users 
WHERE age > (SELECT AVG(age) FROM users);

-- Get orders more expensive than average
SELECT * FROM orders 
WHERE amount > (SELECT AVG(amount) FROM orders);
```

### Subqueries in FROM

```sql
SELECT age_group, COUNT(*) as count
FROM (
    SELECT 
        name,
        CASE 
            WHEN age < 30 THEN 'Young'
            WHEN age < 60 THEN 'Middle'
            ELSE 'Senior'
        END as age_group
    FROM users
) subquery
GROUP BY age_group;
```

### Common Table Expressions (CTE) - WITH Clause

```sql
-- Single CTE
WITH user_orders AS (
    SELECT user_id, COUNT(*) as order_count, SUM(amount) as total_spent
    FROM orders
    GROUP BY user_id
)
SELECT u.name, uo.order_count, uo.total_spent
FROM users u
JOIN user_orders uo ON u.id = uo.user_id;

-- Multiple CTEs
WITH 
monthly_sales AS (
    SELECT DATE_TRUNC('month', order_date) as month, SUM(amount) as sales
    FROM orders
    GROUP BY DATE_TRUNC('month', order_date)
),
avg_sales AS (
    SELECT AVG(sales) as average
    FROM monthly_sales
)
SELECT month, sales, 
    ROUND(sales - (SELECT average FROM avg_sales), 2) as difference
FROM monthly_sales;
```

### Recursive CTE

```sql
-- Generate numbers 1 to 10
WITH RECURSIVE numbers AS (
    SELECT 1 as n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 10
)
SELECT * FROM numbers;

-- Organizational hierarchy
WITH RECURSIVE org_tree AS (
    SELECT id, name, manager_id, 1 as level
    FROM employees
    WHERE manager_id IS NULL
    
    UNION ALL
    
    SELECT e.id, e.name, e.manager_id, ot.level + 1
    FROM employees e
    JOIN org_tree ot ON e.manager_id = ot.id
)
SELECT * FROM org_tree;
```

---

## Advanced Queries

### Window Functions

#### ROW_NUMBER, RANK, DENSE_RANK

```sql
-- Row number (unique rank for each row)
SELECT 
    name, 
    salary,
    ROW_NUMBER() OVER (ORDER BY salary DESC) as rank
FROM employees;

-- RANK (same value = same rank, skip numbers)
SELECT 
    name, 
    salary,
    RANK() OVER (ORDER BY salary DESC) as rank
FROM employees;

-- DENSE_RANK (same value = same rank, no skipping)
SELECT 
    name, 
    salary,
    DENSE_RANK() OVER (ORDER BY salary DESC) as rank
FROM employees;

-- RANK partitioned by department
SELECT 
    department,
    name, 
    salary,
    RANK() OVER (PARTITION BY department ORDER BY salary DESC) as dept_rank
FROM employees;
```

#### LAG & LEAD (Access previous/next rows)

```sql
-- Get previous employee's salary
SELECT 
    name,
    salary,
    LAG(salary) OVER (ORDER BY hire_date) as prev_salary,
    LEAD(salary) OVER (ORDER BY hire_date) as next_salary
FROM employees;

-- Calculate month-to-month change
SELECT 
    DATE_TRUNC('month', order_date) as month,
    SUM(amount) as sales,
    LAG(SUM(amount)) OVER (ORDER BY DATE_TRUNC('month', order_date)) as prev_month_sales
FROM orders
GROUP BY DATE_TRUNC('month', order_date);
```

#### FIRST_VALUE & LAST_VALUE

```sql
-- Get first/last value in partition
SELECT 
    department,
    name,
    salary,
    FIRST_VALUE(name) OVER (PARTITION BY department ORDER BY hire_date) as first_hire,
    LAST_VALUE(name) OVER (PARTITION BY department ORDER BY salary DESC) as highest_paid
FROM employees;
```

#### Running Totals & Aggregations

```sql
-- Cumulative sum
SELECT 
    DATE(order_date) as date,
    amount,
    SUM(amount) OVER (ORDER BY order_date) as cumulative_sales
FROM orders;

-- Average of last 3 rows
SELECT 
    date,
    revenue,
    AVG(revenue) OVER (ORDER BY date ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) as moving_avg_3
FROM daily_sales;
```

### UNION & UNION ALL

```sql
-- UNION removes duplicates
SELECT name, email FROM users
UNION
SELECT name, email FROM archived_users;

-- UNION ALL keeps duplicates
SELECT name, email FROM users
UNION ALL
SELECT name, email FROM archived_users;
```

### INTERSECT & EXCEPT

```sql
-- INTERSECT - common records
SELECT id FROM users
INTERSECT
SELECT user_id FROM orders;

-- EXCEPT - records in first query but not second
SELECT id FROM users
EXCEPT
SELECT user_id FROM orders;
```

### EXISTS

```sql
-- Check if subquery returns any rows
SELECT * FROM users u
WHERE EXISTS (
    SELECT 1 FROM orders WHERE user_id = u.id
);

-- NOT EXISTS
SELECT * FROM users u
WHERE NOT EXISTS (
    SELECT 1 FROM orders WHERE user_id = u.id
);
```

### PIVOT / Cross-tabulation

```sql
-- MySQL example
SELECT 
    month,
    SUM(CASE WHEN product = 'A' THEN amount ELSE 0 END) as product_a,
    SUM(CASE WHEN product = 'B' THEN amount ELSE 0 END) as product_b,
    SUM(CASE WHEN product = 'C' THEN amount ELSE 0 END) as product_c
FROM sales
GROUP BY month;
```

### Complex Joins with Conditions

```sql
-- Join with multiple conditions
SELECT u.name, o.order_id
FROM users u
JOIN orders o ON u.id = o.user_id AND o.status = 'completed';

-- Anti-join (find users without orders)
SELECT u.name
FROM users u
LEFT JOIN orders o ON u.id = o.user_id
WHERE o.id IS NULL;
```

---

## Database Management

### ALTER TABLE

```sql
-- Add column
ALTER TABLE users ADD COLUMN phone VARCHAR(20);

-- Drop column
ALTER TABLE users DROP COLUMN phone;

-- Modify column
ALTER TABLE users MODIFY COLUMN age INT NOT NULL;

-- Add constraint
ALTER TABLE users ADD CONSTRAINT unique_email UNIQUE (email);

-- Drop constraint
ALTER TABLE users DROP CONSTRAINT unique_email;

-- Rename column
ALTER TABLE users RENAME COLUMN age TO user_age;

-- Rename table
ALTER TABLE users RENAME TO app_users;
```

### UPDATE

```sql
-- Update specific rows
UPDATE users SET age = 30 WHERE id = 1;

-- Update with expression
UPDATE users SET age = age + 1 WHERE age < 30;

-- Update with JOIN
UPDATE users u
JOIN orders o ON u.id = o.user_id
SET u.last_order_date = o.order_date
WHERE o.status = 'completed';

-- Update all rows
UPDATE users SET updated_at = NOW();
```

### DELETE

```sql
-- Delete specific rows
DELETE FROM users WHERE age < 18;

-- Delete with JOIN
DELETE u FROM users u
WHERE NOT EXISTS (
    SELECT 1 FROM orders WHERE user_id = u.id
);

-- Delete all rows (keep table structure)
DELETE FROM users;

-- Drop table (remove structure too)
DROP TABLE users;
```

### Indexes

```sql
-- Create index
CREATE INDEX idx_email ON users(email);

-- Create composite index
CREATE INDEX idx_name_email ON users(name, email);

-- Create unique index
CREATE UNIQUE INDEX idx_unique_email ON users(email);

-- Drop index
DROP INDEX idx_email ON users;

-- View indexes
SHOW INDEX FROM users;
```

### Views

```sql
-- Create view
CREATE VIEW user_order_summary AS
SELECT u.id, u.name, COUNT(o.id) as order_count, SUM(o.amount) as total_spent
FROM users u
LEFT JOIN orders o ON u.id = o.user_id
GROUP BY u.id, u.name;

-- Query view
SELECT * FROM user_order_summary;

-- Drop view
DROP VIEW user_order_summary;
```

---

## Performance Tips

### Query Optimization

#### Use EXPLAIN to analyze queries

```sql
EXPLAIN SELECT * FROM users WHERE email = 'john@example.com';
EXPLAIN ANALYZE SELECT * FROM users WHERE email = 'john@example.com';
```

#### Index Best Practices

```sql
-- Index columns used in WHERE clauses
CREATE INDEX idx_status ON orders(status);

-- Index columns used in JOIN conditions
CREATE INDEX idx_user_id ON orders(user_id);

-- Index columns used in ORDER BY
CREATE INDEX idx_created_at ON orders(created_at DESC);

-- Composite index for common filter combinations
CREATE INDEX idx_status_user ON orders(status, user_id);
```

#### Avoid these patterns

```sql
-- ❌ DON'T: Use functions on indexed columns
SELECT * FROM users WHERE YEAR(created_at) = 2023;
SELECT * FROM users WHERE LOWER(name) = 'john';

-- ✅ DO: Use proper date range
SELECT * FROM users WHERE created_at >= '2023-01-01' AND created_at < '2024-01-01';
SELECT * FROM users WHERE name = 'John';

-- ❌ DON'T: Use wildcards at start of LIKE
SELECT * FROM users WHERE email LIKE '%@gmail.com';

-- ✅ DO: Use wildcards at end
SELECT * FROM users WHERE email LIKE 'john%';

-- ❌ DON'T: SELECT * if you need specific columns
SELECT * FROM users;

-- ✅ DO: Select specific columns
SELECT id, name, email FROM users;

-- ❌ DON'T: Use OR with different columns
SELECT * FROM users WHERE email = 'john@example.com' OR phone = '123456';

-- ✅ DO: Use UNION if indexes exist on both columns
SELECT * FROM users WHERE email = 'john@example.com'
UNION
SELECT * FROM users WHERE phone = '123456';
```

### Batch Operations

```sql
-- Insert multiple rows at once (more efficient than individual inserts)
INSERT INTO users (name, email, age) 
VALUES 
    ('John', 'john@example.com', 25),
    ('Jane', 'jane@example.com', 28),
    ('Bob', 'bob@example.com', 35);

-- Update in batches if dealing with large datasets
UPDATE users SET status = 'active' WHERE created_at < NOW() - INTERVAL 30 DAY LIMIT 1000;
```

### Query Optimization Examples

```sql
-- ❌ Inefficient: Multiple subqueries
SELECT u.name, 
    (SELECT COUNT(*) FROM orders WHERE user_id = u.id) as order_count,
    (SELECT SUM(amount) FROM orders WHERE user_id = u.id) as total_spent
FROM users u;

-- ✅ Efficient: Use JOIN and aggregate
SELECT u.name, COUNT(o.id) as order_count, SUM(o.amount) as total_spent
FROM users u
LEFT JOIN orders o ON u.id = o.user_id
GROUP BY u.id, u.name;
```

---

## Quick Reference Cheat Sheet

| Concept | Syntax | Purpose |
| --- | --- | --- |
| SELECT | `SELECT col FROM table` | Retrieve data |
| WHERE | `WHERE condition` | Filter rows |
| JOIN | `INNER/LEFT/RIGHT JOIN` | Combine tables |
| GROUP BY | `GROUP BY column` | Aggregate data |
| HAVING | `HAVING condition` | Filter groups |
| ORDER BY | `ORDER BY column` | Sort results |
| LIMIT | `LIMIT n` | Restrict rows |
| INSERT | `INSERT INTO table VALUES` | Add data |
| UPDATE | `UPDATE table SET` | Modify data |
| DELETE | `DELETE FROM table` | Remove data |
| CREATE | `CREATE TABLE/DATABASE` | Create objects |
| ALTER | `ALTER TABLE` | Modify structure |
| INDEX | `CREATE INDEX` | Speed up queries |
| VIEW | `CREATE VIEW` | Virtual table |
| CTE | `WITH name AS (...)` | Named subquery |
| UNION | `UNION / UNION ALL` | Combine queries |
| WINDOW | `OVER (PARTITION BY)` | Advanced analytics |

---

## Common SQL Functions

### String Functions

```sql
UPPER(string)          -- Convert to uppercase
LOWER(string)          -- Convert to lowercase
LENGTH(string)         -- Get string length
SUBSTR(string, start, length) -- Extract substring
TRIM(string)           -- Remove leading/trailing spaces
CONCAT(str1, str2)     -- Concatenate strings
REPLACE(string, from, to) -- Replace text
```

### Numeric Functions

```sql
ROUND(number, decimals) -- Round number
CEIL(number)           -- Round up
FLOOR(number)          -- Round down
ABS(number)            -- Absolute value
POWER(base, exponent)  -- Raise to power
SQRT(number)           -- Square root
MOD(a, b)              -- Modulo (remainder)
```

### Date Functions

```sql
NOW() / CURRENT_TIMESTAMP -- Current date and time
DATE(datetime)         -- Extract date
YEAR(date)             -- Extract year
MONTH(date)            -- Extract month
DAY(date)              -- Extract day
DATE_ADD(date, INTERVAL n DAY) -- Add days
DATE_SUB(date, INTERVAL n DAY) -- Subtract days
DATEDIFF(date1, date2) -- Difference in days
```

---

**Last Updated:** 2024 **Remember:** Practice these queries regularly to master SQL!