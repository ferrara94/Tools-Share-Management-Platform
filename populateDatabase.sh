#!/bin/bash

# Wait for the Postgres container to be ready
echo "Waiting for Postgres container to be ready..."
until sudo docker exec postgres-sql-tsmp pg_isready -U username > /dev/null 2>&1; do
  echo "Postgres is not ready yet, waiting..."
  sleep 2
done

echo "Postgres is ready, inserting user and tools..."
echo "Inserting a user..."
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"role\" (
  id,
  created_at,
  last_modified_date,
  name
)
VALUES (
  1,
  NOW(),
  NULL,
  'USER'
);
"

echo "Inserting a user..."
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"_user\" (
  id,
  first_name,
  last_name,
  date_of_birth,
  email,
  password,
  account_locked,
  enabled,
  created_at
)
VALUES (
  2,
  'Lucas',
  'Smith',
  '1995-07-15',
  'lucas@example.com',
    '\$2a\$10\$JWMVxush/Ryp/NtIE5BaVOiYr5FA8rXHUvc8HD80RfRVVsyH75JZ6',
  false,
  true,
  NOW()
)
ON CONFLICT (email) DO NOTHING;
"

# Insert a tool
echo "Inserting a tool..."
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"tool\" (
  id,
  created_by,
  created_date,
  last_modified_by,
  last_modified_date,
  name,
  description,
  condition,
  category,
  tool_picture,
  available,
  archived,
  owner_id
)
VALUES (
  1,
  1,
  NOW(),
  NULL,
  NULL,
  'Voltage Tester',
  'A tool used for testing electrical voltage.',
  'New',
  'Electrical',
  'VoltageTester',
  true,
  false,
  1
);
"


echo "User and tools inserted successfully."