#!/bin/bash

# Wait for the Postgres container to be ready
echo "Waiting for Postgres container to be ready..."
until sudo docker exec postgres-sql-tsmp pg_isready -U username > /dev/null 2>&1; do
  echo "Postgres is not ready yet, waiting..."
  sleep 2
done

# ------------------------------------------------------------------
# ROLE TABLE
# ------------------------------------------------------------------


echo "Inserting a role..."
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

# ------------------------------------------------------------------
# USER TABLE
# ------------------------------------------------------------------

echo "Inserting a user - LUCAS"
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

echo "Inserting a user - MARK"
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
  1,
  'Mark',
  'Rossi',
  '1998-08-17',
  'mark@example.com',
    '\$2a\$10\$JWMVxush/Ryp/NtIE5BaVOiYr5FA8rXHUvc8HD80RfRVVsyH75JZ6',
  false,
  true,
  NOW()
)
ON CONFLICT (email) DO NOTHING;
"

echo "Inserting a user - Philip"
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
  3,
  'Philip',
  'Truman',
  '1993-05-21',
  'philip@example.com',
    '\$2a\$10\$JWMVxush/Ryp/NtIE5BaVOiYr5FA8rXHUvc8HD80RfRVVsyH75JZ6',
  false,
  false,
  NOW()
)
ON CONFLICT (email) DO NOTHING;
"


# ------------------------------------------------------------------
# TOOL TABLE
# ------------------------------------------------------------------


echo "Inserting a tool - Voltage Tester - Owner Mark"
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

echo "Inserting a tool - Claw Hammer - Owner Mark"
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
  2,
  1,
  NOW(),
  NULL,
  NULL,
  'Claw Hammer',
  'A hammer with a curved claw for removing nails.',
  'Good state',
  'Carpentry',
  'ClawHammer',
  true,
  false,
  1
);
"

echo "Inserting a tool - Soldering Iron - Owner Mark"
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
  3,
  1,
  NOW(),
  NULL,
  NULL,
  'Soldering Iron',
  'A tool for melting solder to join electrical components.',
  'Good state',
  'Electrical',
  'SolderingIron',
  true,
  false,
  1
);
"

echo "Inserting a tool - Utility Knife - Owner Mark"
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
  4,
  1,
  NOW(),
  NULL,
  NULL,
  'Utility Knife',
  'A knife with a retractable blade used for general cutting tasks.',
  'New',
  'General',
  'UtilityKnife',
  true,
  false,
  1
);
"

echo "Inserting a tool - Measuring Tape - Owner Mark"
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
  5,
  1,
  NOW(),
  NULL,
  NULL,
  'Measuring Tape',
  'A flexible ruler used to measure size or distance.',
  'Normal',
  'Measuring',
  'MeasuringTape',
  true,
  false,
  1
);
"

echo "Inserting a tool - Wire Stripper - Owner Mark"
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
  6,
  1,
  NOW(),
  NULL,
  NULL,
  'Wire Stripper',
  'A tool used to strip insulation from electrical wires.',
  'New',
  'Electrical',
  'WireStripper',
  true,
  false,
  1
);
"

echo "Inserting a tool - Spirit Level - Owner LUCAS"
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
   7,
   2,
   NOW(),
   NULL,
   NULL,
   'Spirit Level',
   'A tool used to check if a surface is level.',
   'New',
   'Measuring',
   'SpiritLevel',
   true,
   false,
   2
 );
"

echo "Inserting a tool - Handsaw - Owner LUCAS"
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
  8,
  2,
  NOW(),
  NULL,
  NULL,
  'Handsaw',
  'A hand tool used for cutting wood.',
  'New',
  'Carpentry',
  'Handsaw',
  true,
  false,
  2
);
"

echo "Inserting a tool - Adjustable Wrench - Owner Mark"
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
  9,
  1,
  NOW(),
  NULL,
  NULL,
  'Adjustable Wrench',
  'A wrench with a movable jaw for gripping different sized fasteners.',
  'Good',
  'Mechanical',
  'AdjustableWrench',
  true,
  false,
  1
);
"


echo "User and tools inserted successfully."