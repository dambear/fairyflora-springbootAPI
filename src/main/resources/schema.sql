

CREATE TABLE IF NOT EXISTS Branch (
    id SERIAL PRIMARY KEY,
    barangay VARCHAR(50) NOT NULL,
    municipality VARCHAR(50) NOT NULL,
    province VARCHAR(50) NOT NULL,
    openingTime TIME NOT NULL,
    closingTime TIME NOT NULL,
    emailAddress VARCHAR(100) NOT NULL,
    -- List of Employees
    assignedEmployees JSONB NOT NULL,  -- Using JSONB for better performance
    dateEstablished DATE NOT NULL,
    version INT
);

CREATE TABLE IF NOT EXISTS Employee (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    middleName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    branchId INT NOT NULL,
    salary INT NOT NULL,
    contactNumber INT NOT NULL,
    dateHired DATE NOT NULL,
    role VARCHAR(50) NOT NULL,
    emailAddress VARCHAR(100) NOT NULL,
    version INT,
    -- ON DELETE CASCADE: This option allows for automatic deletion of employees if the associated branch is deleted.
    FOREIGN KEY (branchId) REFERENCES Branch(id) ON DELETE CASCADE
    );




