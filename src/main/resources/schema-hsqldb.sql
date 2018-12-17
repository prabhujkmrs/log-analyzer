CREATE TABLE IF NOT EXISTS event (
    id VARCHAR(20) ,
    state VARCHAR(50),
    timestamp BIGINT,
    type VARCHAR(50),
    host VARCHAR(20),
    alert BOOLEAN
);