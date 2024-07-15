-- Inserting rate information
INSERT INTO Rate_Info (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES ('Ladder', 1.99, TRUE, TRUE, FALSE);
INSERT INTO Rate_Info (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES ('Chainsaw', 1.49, TRUE, FALSE, TRUE);
INSERT INTO Rate_Info (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES ('Jackhammer', 2.99, TRUE, FALSE, FALSE);

-- Inserting tools
INSERT INTO Tool (tool_code, tool_type, brand) VALUES ('CHNS', 'Chainsaw', 'Stihler');
INSERT INTO Tool (tool_code, tool_type, brand) VALUES ('LADW', 'Ladder', 'Werner');
INSERT INTO Tool (tool_code, tool_type, brand) VALUES ('JAKD', 'Jackhammer', 'DeWalt');
INSERT INTO Tool (tool_code, tool_type, brand) VALUES ('JAKR', 'Jackhammer', 'Ridgid');

-- Inserting holidays
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Independence Day', '2015-07-04', TRUE, 'American Independence Day');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Independence Day', '2020-07-04', TRUE, 'American Independence Day');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Independence Day', '2024-07-04', TRUE, 'American Independence Day');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Labor Day', '2015-09-07', FALSE, 'Celebrated on the first Monday in September');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Labor Day', '2020-09-07', FALSE, 'Celebrated on the first Monday in September');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Labor Day', '2024-09-02', FALSE, 'Celebrated on the first Monday in September');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Thanksgiving Day', '2024-11-28', FALSE, 'Celebrated on the fourth Thursday in November');
INSERT INTO holiday (name, date, is_recurring, description) VALUES ('Christmas Day', '2024-12-25', TRUE, 'Celebration of Christmas');