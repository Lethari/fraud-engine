CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              transaction_reference VARCHAR(255) NOT NULL UNIQUE,
                              customer_id VARCHAR(255) NOT NULL,
                              amount DECIMAL(19, 2) NOT NULL,
                              currency VARCHAR(3) NOT NULL,
                              category VARCHAR(255) NOT NULL,
                              location VARCHAR(255) NOT NULL,
                              country VARCHAR(100) NOT NULL,
                              transaction_date_time DATETIME NOT NULL,
                              transaction_type VARCHAR(50) NOT NULL
);

CREATE TABLE fraud_alerts (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              transaction_id BIGINT NOT NULL,
                              rule_name VARCHAR(255) NOT NULL,
                              reason VARCHAR(500) NOT NULL,
                              severity VARCHAR(255) NOT NULL,
                              detected_at DATETIME NOT NULL,
                              status VARCHAR(255) NOT NULL,

                              CONSTRAINT fk_fraud_alert_transaction
                                  FOREIGN KEY (transaction_id)
                                      REFERENCES transactions(id)
);