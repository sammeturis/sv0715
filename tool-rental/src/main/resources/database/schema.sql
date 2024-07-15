CREATE TABLE IF NOT EXISTS tool (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tool_code VARCHAR(255) NOT NULL ,
    tool_type VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS rate_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tool_type VARCHAR(255) NOT NULL UNIQUE,
    daily_charge DECIMAL(5, 2) NOT NULL,
    weekday_charge BOOLEAN NOT NULL,
    weekend_charge BOOLEAN NOT NULL,
    holiday_charge BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS holiday (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    is_recurring BOOLEAN NOT NULL DEFAULT FALSE,
    description TEXT
);

--Add foreign key constraint to tool table
ALTER TABLE tool
ADD CONSTRAINT fk_tool_rate_info
FOREIGN KEY (tool_type) 
REFERENCES rate_info(tool_type)
ON DELETE SET NULL
ON UPDATE CASCADE;