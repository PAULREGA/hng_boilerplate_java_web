-- Add the column without NOT NULL constraint
ALTER TABLE products ADD COLUMN price INT;

-- Update existing rows with a default value
UPDATE products SET price = 0 WHERE price IS NULL;

-- Add the NOT NULL constraint
ALTER TABLE products ALTER COLUMN price SET NOT NULL;
